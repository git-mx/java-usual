package com.shyfay.usual.thread.concurrent;

import com.sun.javafx.css.CalculatedValue;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

/**
 * 本例用于演示concurrent包下的ExecutorService执行器（线程池）
 * 本例实现的功能是使用多线程来计算1至10000000的正整数之和
 * @author mx
 * @since 2019/8/21
 */
public class ExecutorServiceTest {
    static class ExecutorServiceCalculator{
        private int cpuCount;
        private ExecutorService pool;

        public ExecutorServiceCalculator(){
            //获取CPU的核心跳数
            cpuCount = Runtime.getRuntime().availableProcessors();
            pool = Executors.newFixedThreadPool(cpuCount);
        }

        //处理计算任务的线程
        private static class SumTask implements Callable<Long> {
            private long[] numbers;
            private int from;
            private int to;
            public SumTask(long[] numbers, int from, int to){
                this.numbers = numbers;
                this.from = from;
                this.to = to;
            }

            @Override
            public Long call(){
                long total = 0;
                for(int i=from; i<=to; i++){
                    total += numbers[i];
                }
                return total;
            }
        }

        public long sumUp(long[] numbers){
            List<Future<Long>> results = new ArrayList<>();
            int part = numbers.length / cpuCount;
            for(int i=0; i<cpuCount; i++){
                int from = i * part;
                int to = (i == cpuCount - 1) ? numbers.length - 1 : (i + 1) * part - 1;
                results.add(pool.submit(new SumTask(numbers, from, to)));
            }

            long total = 0L;
            for(Future<Long> f : results){
                try{
                    total += f.get();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        Instant start = Instant.now();
        ExecutorServiceCalculator calculator = new ExecutorServiceCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时:" + Duration.between(start, end).toMillis());
        System.out.println("结果:" + result);
    }
}
