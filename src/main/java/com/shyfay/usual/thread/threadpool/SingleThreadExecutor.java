package com.shyfay.usual.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本例用于演示JAVA常见的4中线程池之一 SingleThreadExecutor
 * Executors.newSingleThreadExecutor();创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有的任务按照执行顺序执行（FIFO，LIFO， 优先级）
 *
 * @author mx
 * @since 2019/8/21
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        //创建一个单线程化的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for(int i=0; i<10; i++){
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //结果依次输出，相当于顺序执行各个任务
                    System.out.println(Thread.currentThread().getName() + "正在执行,index=" + index);
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
