package com.shyfay.usual.thread.future.java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Notes 本例使用FutureTask模拟实现批处理任务，注意看与Future的区别
 * FutureTask是唯一实现了RunableFuture接口的实现类（实现了Future+Runnable）
 * @Author muxue
 * @Since 8/6/2020
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {
        List<Integer> results = new ArrayList<>();
        List<FutureTask<Integer>> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Long start = Instant.now().toEpochMilli();
        for(int i=0; i<10; i++){
            FutureTask<Integer> task = new FutureTask<>(new CallableTask(i + 1));
            executor.submit(task);
            tasks.add(task);
        }
        Long getResultStart = Instant.now().toEpochMilli();
        System.out.println("结果归集开始时间：" + LocalDateTime.now());
        while(tasks.size() > 0){
            Iterator<FutureTask<Integer>> iterator = tasks.iterator();
            while(iterator.hasNext()){
                FutureTask<Integer> task = iterator.next();
                if(task.isDone() && !task.isCancelled()){
                    Integer result = task.get();
                    results.add(result);
                    iterator.remove();
                    System.out.println("获取到结果result=" + result + ":" + LocalDateTime.now());
                }else{
                    TimeUnit.MILLISECONDS.sleep(1);
                }
            }
        }
        System.out.println("results=" + results);
        System.out.println("总耗时=" + (Instant.now().toEpochMilli() - start) + "ms");
        System.out.println("结果集归集耗时=" + (Instant.now().toEpochMilli() - getResultStart) + "ms");
        executor.shutdown();
    }
}
