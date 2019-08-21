package com.shyfay.usual.thread.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 本例用于演示concurrent包的ForkJoinPool的使用方法
 * 本例的功能是实现计算1到10000000进行累加的结果
 * ForkJoinPool提供两种任务类型RecursiveTask和RecursiveAction，
 * RecursiveTask是有返回值的，RecursiveAction是没有返回值的
 * 使用ForkJoinPool有一个特别大的好处，就是可以动态的调节任务分割的阈值，
 * 例如下面的例子可以通过不断的调试来改变任务拆分的阈值，从而更快的得到计算结果。
 * @author mx
 * @since 2019/8/21
 */
public class ForkJoinPoolTest {
    private ForkJoinPool pool;

    public ForkJoinPoolTest() {
        pool = new ForkJoinPool();
    }
    //执行任务RecursiveTask：有返回值 RecursiveAction：无返回值
    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to){
            this.numbers = numbers;
            this.from  = from;
            this.to = to;
        }

        //此方法为ForkJoin的核心方法：对任务进行拆分， 拆分的好坏决定了效率的高低
        @Override
        protected Long compute(){
            //任务拆分的阈值，直接采用for循环计算，否则拆分任务
            if(to - from < 100000){
                long total = 0;
                for(int i=from; i<=to; i++){
                    total += numbers[i];
                }
                return total;
            }else{
                int middle = (from + to) / 2;
                SumTask taskLeft =new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }

    public Long sunAll(long[] numbers){
        Long result = pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
        pool.shutdown();
        return result;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(0, 10000000).toArray();
        ForkJoinPoolTest forkJoinPoolTest = new ForkJoinPoolTest();
        Instant start = Instant.now();
        long result = forkJoinPoolTest.sunAll(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis());
        System.out.println("结果：" + result);
    }
}
