package com.shyfay.usual.thread.future.java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Notes 本例用于演示使用java8提供的CompletableFuture完成相同的任务
 * @Author muxue
 * @Since 8/6/2020
 */
public class CompletableFutureDemo1 {

    public static Integer callTask(Integer value){
        try {
            if(1 == value){
                TimeUnit.MILLISECONDS.sleep(3000);
            }else if(5 == value){
                TimeUnit.MILLISECONDS.sleep(5000);
            }else{
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            return value;
        } catch(InterruptedException e) {

        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        Long start = Instant.now().toEpochMilli();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Integer> results = new ArrayList<>();

        final List<Integer> tasks = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        //方式1，流式处理
//        List<CompletableFuture<Integer>> futures = new ArrayList<>();
//        //循环创建futures，然后组装返回一个有返回值的CompletableFuture，然后调用get()获取
//        for(int i=0; i<tasks.size(); i++){
//            int j=i;
//            CompletableFuture<Integer> future = CompletableFuture
//                    .supplyAsync(() -> callTask(tasks.get(j)), executor)
//                    .whenComplete((v, e) -> {
//                        System.out.println("任务" + v + "完成，异常e=" + e + "，完成时间=" + LocalDateTime.now());
//                        results.add(v);
//                    });
//            futures.add(future);
//        }
//        //流式获取结果：此处是根据任务添加顺序获取结果
//        //1.构造一个空CompletableFuture，子任务数是tasks.size()
//        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
//        //2.流式（总任务完成后，每个子任务join获取结果，然后转换为List）
//        List<Integer> results2 = allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList())).get();
        //方式2：全流式处理换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕
        //返回结果使用whenComplete获取
        List<Integer> results2 = new ArrayList<>();
        CompletableFuture<Integer>[] futures = tasks.stream().map(i -> {
            //把计算任务交给CompletableFuture去异步执行
            return CompletableFuture.supplyAsync(() -> callTask(i), executor)
                    .whenComplete((v, e) -> {
                        System.out.println("任务" + v + "完成,完成时间=" + LocalDateTime.now());
                        results2.add(v);
                    });
        }).toArray(CompletableFuture[]::new);
        //等待总任务完成，但是封装后无返回值，必须自己whenComplete获取，此处使用join来获取结果
        CompletableFuture.allOf(futures).join();
        System.out.println("任务完成先后顺序， 结果result2=" + results2 + ", 任务提交顺序，结果results=" + results);
        System.out.println("耗时" + (Instant.now().toEpochMilli() - start) + "ms");
        executor.shutdown();

    }
}
