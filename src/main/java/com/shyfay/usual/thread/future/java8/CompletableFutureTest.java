package com.shyfay.usual.thread.future.java8;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Notes CompletableFuture主要用于构建异步批处理任务应用
 * 这是java8提供的重磅类库，主要用于解决为Future提供一个回调，让Future在结束之后主动调用
 * 设置的回调函数，而不用主线程去轮询或者阻塞get。
 * CompletableFuture 里有很多很多的API
 * get() 阻塞获取结果同Future.get()
 * join() 获取结果或者抛出异常
 * @Author muxue
 * @Since 8/5/2020
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1
//        CompletableFuture<String> completableFuture = new CompletableFuture<>();
//        new Thread(() -> {
//            System.out.println("task doing...");
//            try {
//                TimeUnit.MILLISECONDS.sleep(300);
//                //System.out.println(1/0);
//            } catch (InterruptedException e) {
//                completableFuture.completeExceptionally(e);
//                e.printStackTrace();
//            }
//            completableFuture.complete("ok");
//        }).start();
//        System.out.println("AAAA");
//        String result = completableFuture.get();
//        System.out.println("计算结果：" + result);
        //2
//        //e是异常
//        CompletableFuture.supplyAsync(() -> 100)
//                .thenApplyAsync(e -> e*100)
//                .thenApply(e -> e.toString())
//                .whenComplete((r, e) -> System.out.println(r + "____" + e));

        //3
//        CompletableFuture.supplyAsync(() -> 100)
//                .thenApplyAsync(e -> {
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(5);
//                        System.out.println("sleep end...");
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                    return e + 1;
//                })
//                .thenApply(e -> e.toString())
//                .whenComplete((r, e) -> System.out.println(r + ":" + e));
//        //以上代码不会输入任何内容，因为thenApplyAsync是异步的
//        //还在sleep的时候main线程就直接结束了
//        //加上以下代码即可
//        TimeUnit.MILLISECONDS.sleep(10);

        //4.
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 100)
                .thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println(future.join());

    }
}
