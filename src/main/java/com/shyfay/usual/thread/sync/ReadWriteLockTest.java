package com.shyfay.usual.thread.sync;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Notes 本例用于演示读写锁
 * 读读（非阻塞）、读写（阻塞）、写写（阻塞）
 * 读读 多个线程读只要求没有别的写线程即可多个线程同时读
 * 读写 要求读和写的线程是同一个线程同时持有读锁和写锁
 * 写写 同一时间只能有一个线程持有写锁
 * @Author muxue
 * @Since 7/24/2020
 */
public class ReadWriteLockTest {

    private int value;

    //读方法，读取value时时候上读锁
    public int read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000); //模拟读文件操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "读取到的值为：" + this.value);
        return this.value;
    }

    //写方法，写入新的value值
    public void write(Lock lock, int newValue){
        try {
            lock.lock();
            Thread.sleep(1000);//模拟写操作
            this.value = newValue;
            System.out.println("写线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //读写锁
    private static ReadWriteLock readWriteLock =  new ReentrantReadWriteLock();
    //读锁
    private static Lock readLock = readWriteLock.readLock();
    //写锁
    private static Lock writeLock = readWriteLock.writeLock();
    //普通的锁
    private static Lock normalLock = new ReentrantLock();

    public static void main(String[] args) {
        final ReadWriteLockTest test = new ReadWriteLockTest();
        //读线程
        Runnable readRunnable = () -> test.read(readLock);
        //换成普通锁，可以对比一下执行的效率，从对比结果看出，读写锁对于提升读写效率起到了非常大的作用
        //Runnable readRunnable = () -> test.read(normalLock);
        //写线程
        Runnable writeRunnable = () -> test.write(writeLock, ThreadLocalRandom.current().nextInt());

        //18个线程同时读
        for(int i = 0; i < 18; i++){
            new Thread(readRunnable).start();
        }
        //2个写线程
        for(int i=0; i<2; i++){
            new Thread(writeRunnable).start();
        }
    }

}
