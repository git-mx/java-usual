package com.shyfay.usual.thread.future.java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Notes 使用Future模拟实现批处理
 *  * 起了10个CallableTask任务，如果并行执行，那么总的时间应该是16S
 *  * 但是现在使用Future实现并行计算，只需要5046MS就可以完成任务
 *  * 这个时间刚好比最长的任务5000MS多一点
 * @Author muxue
 * @Since 8/6/2020
 */
public class FutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Integer> results = new ArrayList<>();
        List<Future<Integer>> futures = new ArrayList<>();
        Long start = Instant.now().toEpochMilli();
        //1.快速提交10个任务，每个任务返回一个Future并放入FutureList封装起来，
        //  模拟实现10个任务并行计算
        for(int i=0; i<10; i++){
            futures.add(executor.submit(new CallableTask(i+1)));
        }
        Long getResultStart = Instant.now().toEpochMilli();
        System.out.println("结果归集开始时间=" + LocalDateTime.now());
        //2.结果归集，用迭代器遍历futures,高速轮询（模拟实现并发），任务完成就移除
        while(futures.size() > 0){
            Iterator<Future<Integer>> iterator = futures.iterator();
            //轮询
            while(iterator.hasNext()){
                Future<Integer> future = iterator.next();
                //非阻塞获取，如果任务完成就立马获取结果，并将任务移除批处理
                //如果还没完成就轮询下一个
                if(future.isDone() && !future.isCancelled()){
                    Integer i = future.get();
                    System.out.println("任务i=" + i + "获取完成，移除队列" + LocalDateTime.now());
                    results.add(i);
                    iterator.remove();
                }else{
                    //避免CPU高速运转
                    TimeUnit.MILLISECONDS.sleep(1);
                }
            }
        }
        System.out.println("results=" + results);
        System.out.println("总耗时=" + (Instant.now().toEpochMilli() - start + "ms"));
        System.out.println("结果归集耗时=" + (Instant.now().toEpochMilli() - getResultStart + "ms"));
        executor.shutdown();
    }
}
