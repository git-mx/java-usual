package com.shyfay.usual.thread.concurrent;

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
 * @author mx
 * @since 2019/8/28
 */
public class MainTest {
    private static int cnum = Runtime.getRuntime().availableProcessors();
    private static ExecutorService pool = Executors.newFixedThreadPool(cnum);

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
            Long total = 0L;
            for(int i=from; i<=to; i++){
                total += numbers[i];
            }
            return total;
        }
    }

    private static Long sumAll(long[] numbers){
        int part = numbers.length / cnum;
        int from;
        int to;
        List<Future<Long>> taskList = new ArrayList<>();
        for(int i=0; i<=cnum; i++){
            from = i * part;
            to = (i == cnum) ? numbers.length - 1 : (i + 1) * part - 1;
            taskList.add(pool.submit(new SumTask(numbers, from, to)));
        }

        Long total = 0L;
        for(Future<Long> future : taskList){
            try{
                total += future.get();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(0, 100000000).toArray();
        Instant start = Instant.now();
        Long result = sumAll(numbers);
        System.out.println("结果是：" + result);
        System.out.println("耗时:" + Duration.between(start, Instant.now()).toMillis() + "毫秒");
    }
}
