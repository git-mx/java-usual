package com.shyfay.usual.thread;

import java.util.concurrent.atomic.AtomicInteger;

/** Atomic原子操作
 * 它的实现原理是利用unsafe的CAS来实现同步操作的
 * unsafe的底层是利用C语言实现的，它实现自增实际上只用到了一条CPU指令cmpxchg
 * 即（Compare AND SWAP） 因为是一条CPU指令所以可以实现原子操作
 * CAS的具体操作步骤是：
 * 对比线程栈里变量的值和内存里的值（预期值）是否相等，如果相等则将内存里和线程栈里的值都改成新的值
 * 如果不等则啥也不做，结束这个操作
 * Atomic也是有问题的（ABA问题）
 * 1.线程AB同时读到atomic变量，这是线程A和线程B的内存值和预期值都是10
 * 2.线程A执行COU的cmpxchg命令修改atomic变量的值为11
 * 3.这时线程C进来了，读到的atomic表变量的值和预期值都是11，于是执行cpu的cmpxchg指令将atomic变量的值改回10
 * 4.线程C发现内存值10与自己的预期值10相等，于是调用cmmpxchg指令降值修改成12
 * 这样在整个过程中线程C都不知道这中间变量的值被线程B修改过一次
 * 其实个人觉得这样的问题并不存在，
 * 避免ABA问题的办法是将原来的atomic变量改成AtomicStampedReference和AtomicMarkableReference
 * AtomicStampedReference和AtomicMarkableReference的实现原理是
 * 这两种数据结构维护了一个Pair对象，Pair对象存储线程针对变量的对象引用和一个stamp值。每次进行CAS比较的
 * 时候并不知比较预期值与内存里的值进行比对，而是比对当先持有的Pair对象和既有的Pair对象的
 * 对象引用的stamp是否都相等，如果都相等则修改，否则则表示该变量中间被操作过则啥也不做
 * 所有的原子操作如下：
 * jdk1.5
 * AtomicBoolean
 * AtomicInteger
 * AtomicIntegerArray
 * AtomicIntegerFieldUpdater
 * AtomicLong
 * AtomicLongArray
 * AtomicLongFielUpdater
 * AtomicMarkabelReference
 * AtomicReference
 * AtomicReferenceArray
 * AtomicReferenceFieldUpdater
 * AtomicStampedReference
 * jdk1.8
 * DoubleAccumulator
 * DoubleAdder
 * LongAccumulator
 * LongAdder
 * 如果项目使用的是JDK1.8且变量用于搜集统计数据，
 * 那么建议使用LongAccumulator和LongAdder替代AtomicLong
 * 因为在高并发的情况下LongAccumulator和LongAddr比AtomicLong更高效
 * 如果是细粒度的同步控制，还是老老实实使用AtomicLong吧，因为LongAccumulator和AtomicLong
 * 与AtomicLong在并发量不是特别大的时候性能差不多，而且前者会占用更多空间
 * @author mx
 * @since 2019/7/6
 */
public class Atomic{
    public static AtomicInteger RACE = new AtomicInteger(0);
    public static void increase(){
        RACE.incrementAndGet();
    }
    public static int THREAD_COUNT = 20;
    public static void main(String[] args){
        Thread[] threads = new Thread[THREAD_COUNT];
        for(int i=0; i<THREAD_COUNT; i++){
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for(int j=0; j<10000; j++){
                        increase();
                    }
                }
            });
        }
        for(int i=0; i<THREAD_COUNT; i++){
            threads[i].start();
        }
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(RACE);
    }
}