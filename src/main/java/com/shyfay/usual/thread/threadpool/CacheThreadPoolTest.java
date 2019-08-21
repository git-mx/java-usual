package com.shyfay.usual.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java四中常见的线程池之 cacheThreadPool
 * ExecutorService 是Java提供的用于管理线程池的类，该类有两个作用：控制线程数量和重用线程
 * Executors.newCachedThreadPool()：可缓存线程，先查看池中有没有以前建立的线程，如果有，就直接使用。如果没有，就新建一个线程加入线程池中
 * @author mx
 * @since 2019/8/21
 */
public class CacheThreadPoolTest {
    public static void main(String[] args) {
        //创建一个可缓存线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for(int i=0; i<10; i++){
//            try{
//                //sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
//                Thread.sleep(1);
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //打印正在执行的缓存线程信息
                    System.out.println(Thread.currentThread().getName() + "正在执行");
                }
            });
        }
    }
}
