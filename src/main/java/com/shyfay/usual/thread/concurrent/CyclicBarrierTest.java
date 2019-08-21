package com.shyfay.usual.thread.concurrent;

import java.util.concurrent.CyclicBarrier;

/**
 * 本例用于演示concurrent包的CyclicBarrier栅栏
 * CyclicBarrier的作用是持有栅栏的线程在调用栅栏的await()方法后会阻塞直到栅栏规定的线程数都到达栅栏后并且等到栅栏线程执行完毕之后才会继续执行
 * 栅栏的应用场景是在于多线程计算数据，最后合并计算的时候
 * CountDownLatch与CyclicBarrier的区别是：
 * CountDownLatch是一次性的
 * CountDownLatch参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束，CyclicBarrier参与的线程职责是一样的
 * @author mx
 * @since 2019/8/21
 */
public class CyclicBarrierTest {
    private static class TaskThread extends Thread {
        CyclicBarrier barrier;
        public TaskThread(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run(){
            try{
                Thread.sleep(1000);
                System.out.println(getName() + "到达栅栏");
                barrier.await();
                System.out.println(getName() + "冲破栅栏");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始执行最后的任务");
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结束执行最后的任务");
            }
        });
        for(int i=0; i<threadNum; i++){
            new TaskThread(barrier).start();
        }
    }
}
