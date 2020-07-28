package com.shyfay.usual;



import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Notes 使用阻塞队列处理消费者消费的速度与生产则生产的速度不一致的问题、
 * @Author muxue
 * @Since 7/24/2020
 */
public class TempTest {
    private static class Producer implements Runnable {
        private BlockingQueue<String> messageQueue;
        private volatile boolean isRunning = true;
        public Producer(BlockingQueue<String> messageQueue){
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            while(this.isRunning){
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    messageQueue.put(Thread.currentThread().getName() + ":" + random);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stop(){
            this.isRunning = false;
        }
    }

    private static class Consumer implements Runnable {

        private BlockingQueue<String> messageQueue;

        public Consumer(BlockingQueue<String> messageQueue){
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            String data;
            while(true){
                try {
                    data = this.messageQueue.poll(2, TimeUnit.SECONDS);
                    if(null == data){
                        break;
                    }
                    System.out.println("data:" + data + ", size:" + this.messageQueue.size());
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(10);
        Producer producer1 = new Producer(messageQueue);
        Producer producer2 = new Producer(messageQueue);
        Producer producer3 = new Producer(messageQueue);
        Consumer consumer = new Consumer(messageQueue);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(producer1);
        executor.execute(producer2);
        executor.execute(producer3);
        executor.execute(consumer);
        try {
            TimeUnit.SECONDS.sleep(10);
            producer1.stop();
            producer2.stop();
            producer3.stop();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
