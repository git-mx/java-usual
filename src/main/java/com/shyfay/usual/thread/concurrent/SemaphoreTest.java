package com.shyfay.usual.thread.concurrent;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 本例用于测试concurrent包下的Semaphore信号量
 * Semaphore主要有两个方法acquire和release，计数信号量由一个指定数量的"许可"初始化。
 * 每调用一次acquire()方法那么就意味着一个"许可"就被一个线程取走，当线程执行完自己的逻辑执行信号量的release()方法会把"许可"还回来
 * 信号量主要有两种用途：
 * 保护一个重要（代码）部分防止一次超过N个线程进入
 * 在两个线程之间发送信号量
 * 令牌桶算法的原理就跟这个信号量的原理差不多，它就是使用许可证来限流的
 * @author mx
 * @since 2019/8/21
 */
public class SemaphoreTest {
    private static final Semaphore semaphore = new Semaphore(3);
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    static class InformationThread extends Thread {
        private final String name;
        private final int age;
        public InformationThread(String name, int age){
            this.name = name;
            this.age = age;
        }

        public void run(){
            try{
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " name: " + this.name + " age: " + this.age + " " + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(name + "准备释放许可证 " + System.currentTimeMillis());
                System.out.println("当前可用的许可证数量是：" + semaphore.availablePermits());
                semaphore.release();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String []names = {"AAAA", "BBBB", "CCCC", "DDDD", "EEEE", "FFFF", "GGGG"};
        int []ages = {26, 27, 18, 19, 20, 99, 10};
        for(int i=0; i<7; i++){
            Thread t1 = new InformationThread(names[i], ages[i]);
            threadPool.execute(t1);
        }
    }
}
