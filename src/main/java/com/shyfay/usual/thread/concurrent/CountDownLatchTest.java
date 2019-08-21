package com.shyfay.usual.thread.concurrent;


import java.util.concurrent.CountDownLatch;

/**
 * 本例用于演示concurrent包下的CountDownLatch闭锁 latch就是门闩，插销的意思
 * ountDownLatch的作用是提供一个或多个线程等到一些列操作的完成
 * 下面的例子中，waiter1和waiter2会阻塞等到latch减到0时才执行（注意，并不是等到Decrementer线程执行完了才执行）
 * @author mx
 * @since 2019/8/21
 */
public class CountDownLatchTest {
    private static class Waiter1 implements Runnable {
        CountDownLatch latch = null;
        public Waiter1(CountDownLatch latch){
            this.latch = latch;
        }
        @Override
        public void run(){
            System.out.println("Waiter1开始执行");
            try{
                latch.await();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Waiter1 Released...");
        }
    }

    private static class Waiter2 implements Runnable {
        CountDownLatch latch = null;
        public Waiter2(CountDownLatch latch){
            this.latch = latch;
        }
        @Override
        public void run(){
            System.out.println("Waiter2开始执行");
            try{
                latch.await();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Waiter2 Released...");
        }
    }

    private static class Decrementer implements Runnable {
        CountDownLatch latch = null;
        public Decrementer(CountDownLatch latch){
            this.latch = latch;
        }
        @Override
        public void run(){
            try{
                Thread.sleep(1000);
                this.latch.countDown();
                Thread.sleep(1000);
                this.latch.countDown();
                Thread.sleep(1000);
                this.latch.countDown();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            try{
                Thread.sleep(4000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("CountDown减为0了...");
        }
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Waiter1 waiter1 = new Waiter1(latch);
        Waiter2 waiter2 = new Waiter2(latch);
        Decrementer decrementer = new Decrementer(latch);
        new Thread(waiter1).start();
        new Thread(waiter2).start();
        new Thread(decrementer).start();
    }
}
