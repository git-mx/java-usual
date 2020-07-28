package com.shyfay.usual.thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Notes ReentrantLockTest是唯一实现了Lock接口的实现类
 * 它与Synchronized的区别是：
 * 1.Synchronized是JAVA自己维护的，就是JAVA语言自带的，
 *   而Lock并不是JAVA语言自带的，需要自己实现（ReentrantLock只是它的一种实现而已）
 * 2.Synchronizde锁的释放是JVM自己控制的（即代码块或者同步方法执行完毕或者抛出异常时锁被JVM释放）
 *   而Lock的释放是由程序员自己写释放锁的代码去释放
 * 3.Lock可以提供能细粒度的锁控制，比如如果获取锁失败是否立即返回，或者控制获取锁时等待一定得时间
 * @Author muxue
 * @Since 7/24/2020
 */
public class ReentrantLockTest {

    //设置为true表示公平锁，而默认是false非公平锁
    private Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLockTest test = new ReentrantLockTest();
        new Thread(){
            @Override
            public void run() {
                try {
                    test.say(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    test.say(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //Lock一定要释放锁，如果没有手动编写释放锁的代码（即使是抛出异常），被占用的锁是不会被释放的
    private void say(Thread thread) throws InterruptedException {
        if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
            try {
                System.out.println(thread.getName() + "得到了锁");
                for(int i=0; i<10; i++){
                    System.out.println(thread.getName() + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        }
    }
}
