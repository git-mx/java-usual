package com.shyfay.usual.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author mx
 * @since 2019/7/6
 */
public class FutureThread {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Callable<String> baoziThread = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "包子准备完毕...";
            }
        };
        Callable<String> xifanThread = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "稀饭准备完毕";
            }
        };
        FutureTask<String> baoziFuture = new FutureTask<>(baoziThread);
        FutureTask<String> xifanFuture = new FutureTask<>(xifanThread);
        new Thread(baoziFuture).start();
        new Thread(xifanFuture).start();
        try{
            System.out.println(baoziFuture.get());
            System.out.println(xifanFuture.get());
        }catch(Exception e){
            System.out.println("Thread has been interrupted...");
            e.printStackTrace();
        }
        System.out.println("准备早饭总共花掉了" + (System.currentTimeMillis() - start) + "毫秒");
    }
}
