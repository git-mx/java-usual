package com.shyfay.usual.thread.concurrent.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Notes LinkedBlockingQueue与ArrayBlockingQueue最大的区别在于它的写入和读取
 * 时使用的是不同的锁对象，这样生产者与消费者完全互不干扰 有三个构造函数
 * 1.空参数构造函数，队列大小默认Integer.MAX_VALUE
 * 2.指定容量的构造函数
 * 3.以一个Collection进行初始化的构造函数，队列大小默认也是Integer.MAX_VALUE
 * 在消费的速度明显大于生产者是的速度的系统中，一定要指定队列的长度
 * 如果不指定导致队列过大造成OOM
 * @Author muxue
 * @Since 8/29/2020
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        for(int i=0; i<5; i++){
            new Thread(() ->{
                for(int j=0; j<1000; j++){
                    try {
                        linkedBlockingQueue.put(Thread.currentThread().getName() + ":" + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for(int i=0; i<10; i++){
            new Thread(() -> {
                while(true){
                    try {
                        String content = linkedBlockingQueue.poll(5, TimeUnit.MILLISECONDS);
                        if(null == content){
                            break;
                        }
                        System.out.println("AAAA:" + content);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "--------------------------------game over");
            }).start();
        }
    }
}
