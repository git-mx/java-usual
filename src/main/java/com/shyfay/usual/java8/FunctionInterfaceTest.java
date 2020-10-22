package com.shyfay.usual.java8;

import com.shyfay.usual.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * @Notes 函数式接口
 * 它是一个只有一个虚方法的接口（Single Abstrac Method），可以在调用的时候，使用一个lambda表达式作为参数
 * Supplier 一个不带参数只有一个返回值的生产者扩展接口
 *          BooleanSupplier getAsBoolean
 *          DoubleSupplier getAsDouble
 *          IntSupplier getAsInt
 *          LongSupplier getAsLong
 * Consumer 比较重要的accept()方法和andThen()方法
 * Predicate 比较重要的and、or、negate和isEqual方法
 * Function 高阶函数
 * Operator 几乎和Function一样,单元算子和二元算子
 *
 * @Author muxue
 * @Since 7/28/2020
 */
public class FunctionInterfaceTest {
    public static void main(String[] args) {
        //1.Supplier<String> supplier = () -> "Default String...";
        //默认空字符串
        Supplier<String> supplier = String::new;
        System.out.println(supplier.get());

        //2.Consumer
        List<User> userList = new ArrayList<>();
        User user;
        for(int i=0; i<20; i++){
            user = new User(1);
            user.setName(i + "name");
            user.setAge(i);
            userList.add(user);
        }
        List<String> strList = new ArrayList<>();
        Consumer<User> consumer = x -> {
          if(x.getName().startsWith("1")){
              strList.add(x.getName());
          }
        };
        //consumer = consumer.andThen(x -> userList.removeIf(y -> y.getAge() < 10));
        userList.forEach(consumer);
        System.out.println(strList);
        userList.removeIf(x -> x.getAge() < 10);
        System.out.println(userList);

        //3.Predicate
        int[] numbers = {1,2,3,4,5,6,7,8,9, 10};
        List<Integer> intList = new ArrayList<>();
        for(int i:numbers){
            intList.add(i);
        }
        Predicate<Integer> p1 = n -> n > 3;
        Predicate<Integer> p2 = n -> n < 9;
        Predicate<Integer> p3 = n -> n%2 == 0;
        List<Integer> filteredList = intList.stream().filter(p1.and(p2).and(p3.negate()).and(Predicate.isEqual(7))).collect(Collectors.toList());
        System.out.println(filteredList);

        //Function
        Function<Integer, Integer> fun1 = n -> n * 2;
        Function<Integer, Integer> fun2 = n -> n * n;
        System.out.println(fun1.apply(4));
        System.out.println(fun2.apply(4));
        System.out.println(fun1.compose(fun2).apply(4));
        System.out.println(fun2.andThen(fun2).apply(4));

        //Operator 一元算子和二元算子
        UnaryOperator<Integer> unaryOperator = n -> n + 10;
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;

        BinaryOperator<Integer> maxOperator = BinaryOperator.maxBy(Integer::compare);

        System.out.println(unaryOperator.apply(10));
        System.out.println(binaryOperator.apply(10, 15));
        System.out.println(maxOperator.apply(10, 15));

        //LongUnaryOperator applyAsLong
        //LongBinaryOperator applyAsLong

    }
}
