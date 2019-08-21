package com.shyfay.usual.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 本例用于演示JAVA四中常见的线程池之一ScheduleThreadPool
 * Executors.newScheduleThreadPool(n); 创建一个定长的线程池，支持定时周期性任务执行
 * @author mx
 * @since 2019/8/21
 */
public class ScheduleThreadPool {
    public static void main(String[] args) {
        //创建一个定长的支持定时任务执行的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("延时1秒执行");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟1秒后每3秒执行一次" + Thread.currentThread().getName());
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
