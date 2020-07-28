package com.shyfay.usual.thread.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Notes 不知道怎么才能模拟让两端代码同时运行
 * 所谓的JAVA内存模型的的共享变量就是指：被对个线程同时使用的对象，比如下一例中的inner
 * @Author muxue
 * @Since 7/27/2020
 */
public class VolatileTest {

    private static class Inner {
        boolean isRunning = false;
        int a = 1;

        public void changeStatus(){
            this.a = 2;
            this.isRunning = true;
        }

        public void run(){
            int b = this.a + 1;
            System.out.println(this.a);
            if(this.isRunning){
                System.out.println(b);
            }
        }
    }

    public static void main(String[] args) {
        Inner inner = new Inner();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            for(int i=0; i<3; i++){
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inner.changeStatus();
        }).start();

        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inner.run();
        }).start();
    }

}
