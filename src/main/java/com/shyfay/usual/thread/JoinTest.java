package com.shyfay.usual.thread;

import java.util.concurrent.TimeUnit;

/**
 * JOIN方法，A线程调用B线程的join方法时，如果没传参数，则A线程必须等待B线程执行完毕才会继续执行
 * 如果传了参数比如2000，那么A线程调用join方法后只会等待B线程执行2000毫秒然后继续执行不会等到B线程执行完毕。
 * @author mx
 * @since 2019/7/6
 */
public class JoinTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    try{
                        Thread.sleep(1000);
                        System.out.println(i);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("Sub thread has been terminated...");
            }
        });
        thread.start();
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread has been terminated...");
    }
}
