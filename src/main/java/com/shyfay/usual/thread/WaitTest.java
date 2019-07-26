package com.shyfay.usual.thread;

import java.util.concurrent.TimeUnit;

/**
 * 这里在thread2中虽然是在i==2的时候唤醒thread1的，但是thread1并没有在thread2调用notify()的时候立即执行
 * 而是等待thread2执行完了才接着继续执行，这是因为虽然thread2在i==2时通知了thread1继续执行，但是由于thread1和
 * thread2都持有同步锁lock，所以只有等到thread2执行完释放同步锁时thread1才能获得该同步锁继续执行
 * 由此可知，wait()方法会释放当前线程的同步锁，而notify()方法则不会释放当前线程持有的同步锁
 * 记住：任何JAVA对象都拥有wait和notify方法。
 * wati() notify() notifyAll() 方法必须在同步语句块中被定义，JVM的硬性规定
 * @author mx
 * @since 2019/7/6
 */
public class WaitTest {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock){
                    try{
                        for(int i=0; i<5; i++){
                            System.out.println("AAAA:" + i);
                            TimeUnit.MILLISECONDS.sleep(100);
                            if(i==3){
                                lock.wait();
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock){
                    try{
                        for(int i=0; i<5; i++){
                            System.out.println("BBBBBBBBB:" + i);
                            TimeUnit.MILLISECONDS.sleep(2000);
                            if(i==2){
                                lock.notify();
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //lock.notifyAll();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
