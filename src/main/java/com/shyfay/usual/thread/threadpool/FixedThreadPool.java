package com.shyfay.usual.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本例用于演示JAVA常见的线程池之一 FixedThreadPool
 * Executors.newFixedThreadPool(n);创建一个可重用固定个数的线程池，以可共享的无界队列方式来运行这些线程。
 * @author mx
 * @since 2019/8/21
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        //创建一个可重用的固定大小的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for(int i=0; i<10; i++){
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        //打印正在执行的缓存线程信息
                        System.out.println(Thread.currentThread().getName() + "正在被执行");
                        Thread.sleep(100);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
