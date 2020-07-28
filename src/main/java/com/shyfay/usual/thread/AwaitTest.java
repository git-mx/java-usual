package com.shyfay.usual.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Notes await和signal/signalAll 是针对ReentrantLock的等待与唤醒，与synchronized的wait与await类似
 * await和aignal/signalAll方法只能由锁持有的Condition对象来调用而不是持有锁的线程
 * JAVA的ArrayBlockingQueue使用数组维护了一个定长的阻塞队列，可以读源码看一下ArrayBlockingQueue的
 * put方法，当执行put方法时，首先调用ReentrantLock类的lockInterruptibly()方法获得锁，
 * 然后判断队列是否满（因为是定长的），如果满了则执行await方法让当前执行put方法的线程挂起，下一句就是insert语句
 * 然后再看ArrayBlockingQueue的extract()方法，再最后返回之前调用了notFull.signal方法，来随机唤醒一个
 * 等待队列中的put操作线程
 * @Author muxue
 * @Since 7/24/2020
 */
public class AwaitTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "==>获得锁，并进入等待");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "==>重新获得锁，继续执行");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "==>获得锁，开始休眠");
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    //随机唤醒一个等待队列中的线程
                    condition.signal();
                    System.out.println(Thread.currentThread().getName() + "==>休眠结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }

            }
        }.start();
    }
}
