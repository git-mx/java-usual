package com.shyfay.usual.thread.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Notes BlokcingQueue五大实现之一ArrayBlockingQueue
 * 基于数组的阻塞队列实现，在ArrayBlockingQueue内部，维护了一个定长数组用于存放数据元素，另外还维护了两个变量
 * 用于标识队列的头部和尾部在数组中的位置，使用数组来实现FIFO嘛，所以必须要明确标识队首元素的数组下标，不然take操作
 * 的时候并不直到哪一个是队首元素
 * ArrayBlockingQueue在生产者放入数据和消费则获取数据都是共用同一个锁对象，由此也意味着无法真正的实现并行
 * ，并发效率低，这点尤其不同于LinkedBlockingQueue，但是相对于LinkedBlockingQueue它也有好处，就是
 * 在插入或删除元素时不会产生或销毁任何额外的对象实例，而后者会产生一个额外的Node对象，这对于内存消耗巨大的系统
 * 中，对于GC负载还是有一定影响的
 * ArrayBlockingQueue的三个构造参数
 * int capacity 数组的长度，这个参数是必须的
 * boolean fair 是否FIFO，默认false
 * Collection<？ extends E> 用于初始化队列元素的集合
 *
 * @Author muxue
 * @Since 8/29/2020
 */
public class ArrayBlockingQueueTest {
    static class Cookie{
        private String number;
        public Cookie(String number){
            this.number = number;
        }
        @Override
        public String toString(){
            return number + "";
        }
    }
    static class Producer extends Thread{
        private static int i = 0;
        private ArrayBlockingQueue<Cookie> arrayBlockingQueue;

        public Producer(ArrayBlockingQueue<Cookie> arrayBlockingQueue){
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run(){
            try {
                while(i < 1000){
                    arrayBlockingQueue.put(new Cookie("cookie" + i));
                    if(++i % 100 == 0){
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consume implements Runnable{
        private ArrayBlockingQueue<Cookie> arrayBlockingQueue;

        public Consume(ArrayBlockingQueue<Cookie> arrayBlockingQueue){
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run() {
            try {
                while(true){
                    Cookie poll = arrayBlockingQueue.poll(5, TimeUnit.SECONDS);
                    if(poll != null){
                        System.out.println(Thread.currentThread().getName() + "--consume--" + poll);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            ArrayBlockingQueue<Cookie> arrayBlockingQueue = new ArrayBlockingQueue<>(10, true);
            for(int i=0; i<1; i++){
                new Producer(arrayBlockingQueue).start();
            }
            for(int i=0; i<5; i++){
                new Thread(new Consume(arrayBlockingQueue)).start();
            }
        }
    }
}
