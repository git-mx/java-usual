package com.shyfay.usual.thread.concurrent.blockingqueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Notes SynchronousQueue是串行的，它的内部并没有一个集合用于存储元素，它只能存放一个元素
 * 当生产者往队列里放入元素的时候，队列得先看仅有得一个元素位置是不是为空，如果为空则放入，否则
 * 阻塞生产者线程，直到消费则线程将队列里唯一的元素取走
 * SynchronousQueue非常适合传递模型
 * @Author muxue
 * @Since 8/29/2020
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Thread producer = new Thread(() -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Integer temp;
            while(true){
                temp = random.nextInt(1000);
                System.out.println("生产者开始生产产品：" + temp);
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.offer(temp);
                System.out.println("产品生产完成");
            }
        });
        Thread consumer = new Thread(() -> {
            Integer product;
            while(true){
                try {
                    product = queue.take();
                    System.out.println("消费者消费了一个产品：" + product);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
        Thread.yield();
    }
}
