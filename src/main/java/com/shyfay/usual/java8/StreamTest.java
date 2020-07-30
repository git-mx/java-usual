package com.shyfay.usual.java8;

import com.shyfay.usual.StaticList;
import com.shyfay.usual.User;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Notes java8中的Stream是对集合对象功能的增强，它专注于对集合对象进行各种非常便利和高效的聚合操作
 * ，或者大批量的数据操作。它提供串行和并行两种模式进行聚合操作，并发模式能够充分利用多核处理器的优势，
 * 使用Fock/Join的方式来拆分任务和加速处理过程。
 * 简单的说stream就是使用实现一个filter-map-reduce过程，产生一个最终结果，或者导致一个副作用
 * 流操作分为两种：中间操作 和 终止操作 一个流的中间操作可以有多个，但是终止操作必须有且仅有一个，
 *              因为一个流只能使用一次。
 * 流的中间操作有：无状态的：unordered filter map mapToInt mapToLong mapToDouble flatMap flatMapToInt
 *              flatMapToLong flatMapToDouble peek
 *              有状态的：distinct storted limit skip
 * 流的结束操作有：非短路操作：forEach forEachOrdered toArray reduce collect max min count
 *              短路操作：anyMatch allMatch noneMath findFirst findAny
 * 迭代器的使用：当集合的元素数量巨大时，一定要使用stream进行迭代
 * @Author muxue
 * @Since 7/29/2020
 */
public class StreamTest {
    //1.一般针对Integer Long Double 的流操作最好使用IntStream LongStream DoubleStream
    //而不是一般的Stream，因为一般的Stream在进行封箱和拆箱过程会比较耗时
    public static void main(String[] args) {
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        //rang和rangClosed都返回一个LongStream rang不包含第二个参数这个值，而rangClose包含
        IntStream.range(1, 3).forEach(System.out::print);
        IntStream.rangeClosed(1, 3).forEach(System.out::print);

        //2.自己生成流
        ThreadLocalRandom random = ThreadLocalRandom.current();
        //一般的流
        Stream.generate(random::nextInt).limit(10).forEach(System.out::print);
        //采用IntStream
        IntStream.generate(random::nextInt).limit(10).forEach(System.out::print);
        //另一种自定义生成流 0
        IntStream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.println(x + " "));

        //3.并行流
        StaticList.users.parallelStream().filter((e) -> e.getAge() >= 10).skip(2).forEachOrdered(System.out::println);

        //4.peek 当元素被消费时执行特定的操作
        //这样并不会输出
        Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e));
        //需要这样，就是必须有终止操作
        List<String> list = Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e)).collect(Collectors.toList());
        //可以多次peek
        Stream.of("one", "two", "three", "four")
                .peek(e -> System.out.println("Peeked value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        //5.unordered 非排序提高效率
        List<Long> longList = new ArrayList<>();
        //使用并行加非排序提高效率，TODO 事实是最好别用并行，会有问题，应该是有BUG的
        LongStream.rangeClosed(5, 10000).unordered().parallel().limit(1000).forEach(e -> longList.add(e));
        System.out.println(longList);

        //6.reduce reduce是一种非常重要的编程思想，它的起到的作用是：
        //提供一个种子，执行流的时候使用种子和流的第一个元素进行reduce里定义的运算，
        //并作为新的种子，然后与流里的第二个元素进行reduce定义的运算，直到消费完所有的流元素
        //比如求和操作
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        //不给种子，针对Integer的默认种子就是零与reduce(0, Integer::sum)的效果是一样的
        Optional<Integer> sumOptional = integers.stream().reduce(Integer::sum);
        System.out.println(sumOptional.get());

        //7.toMap 使用(key, repetitiveKey) -> key 去重取第一个
        Map<Integer, User> userMap = StaticList.users.stream().collect(Collectors.toMap(User::getId, u -> u, (k, rk) -> k));
        Map<Integer, String> nameMap = StaticList.users.stream().collect(Collectors.toMap(User::getId, User::getName, (k, rk) -> k));

        //8.SummaryStatistics
        //List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        IntSummaryStatistics summary = StaticList.users.stream().collect(Collectors.summarizingInt(User::getId));
        System.out.println(summary.getCount());
        System.out.println(summary.getAverage());
        System.out.println(summary.getMax());
        System.out.println(summary.getSum());

        //9.others
        String str = list.stream().collect(Collectors.joining(",", "》》》", "<<<"));
        Optional<Integer> max = integers.stream().collect(Collectors.maxBy(Integer::compare));
        Integer maxAndThen = integers.stream().collect(Collectors.collectingAndThen(Collectors.maxBy(Integer::compare), Optional::get));
        //10.分组，举例，按照省市进行分组
        //Map<Strig, Map<String, List<Article>> result = articles.stream()
        // .collect(Collectors.groupingBy(Article::Province, Collectors.groupingBy(Article::getCity)));
        //分组统计,下面的例子是同类每一类文章的总价
        //Map<String, Long> tradeAmountMap = list.stream().filter(e -> e.getStatus() == 2)
        //.collect(Collectors.groupingBy(Article::getType, Collectors.summingLong(Article::getPrice)));
        //11.分区 只能分成两组
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 5, 5, 6, 9);
        Map<Boolean, List<Integer>> partition = list2.stream().collect(Collectors.partitioningBy(x -> x >= 4 && x % 2 == 0));
        //12.mapping操作跟map很类似
        str = Stream.of("a", "b", "c").collect(Collectors.mapping(e -> e.toUpperCase(), Collectors.joining(",")));
        //Stream的静态方法。concat
//        List<Integer> list1 = Arrays.asList(1,2,3);
//        List<Integer> list2 = Arrays.asList(4,3,2);
//        Stream.concat(list1.stream(),list2.stream()).forEach(System.out::print);


    }
}
