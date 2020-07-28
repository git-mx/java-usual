package com.shyfay.usual.thread;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Notes 本例用于模拟多个生产者和一个消费者的情况
 * @Author muxue
 * @Since 7/24/2020
 */
public class BlockingQueueTest1 {
    private static class Producer implements Runnable {
        private volatile boolean isRunning = true;
        private BlockingQueue dataQueue;
        private static AtomicInteger count = new AtomicInteger();
        public Producer(BlockingQueue dataQueue){
            this.dataQueue = dataQueue;
        }
        @Override
        public void run() {
            String data;
            System.out.println("生产者线程启动...");
            try {
                while(isRunning){
                    data = "data:" + count.incrementAndGet();
                    System.out.println("生产者正在生产数据" + data + "，此时队列的长度是：" + dataQueue.size());
                    TimeUnit.MILLISECONDS.sleep(1000);
                    if(!dataQueue.offer(data, 2, TimeUnit.SECONDS)){
                        System.out.println("放入数据失败：" + data);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }finally {
                System.out.println("生产者线程退出...");
            }
        }

        public void stop(){
            this.isRunning = false;
        }
    }

    private static class Consumer implements Runnable {
        private BlockingQueue dataQueue;

        public Consumer(BlockingQueue dataQueue){
            this.dataQueue = dataQueue;
        }

        @Override
        public void run() {
            System.out.println("消费者线程启动...");
            boolean isRunning = true;
            try {
                while(isRunning){
                    System.out.println("正在从队列里获取数据");
                    String data = (String)dataQueue.poll(2, TimeUnit.SECONDS);
                    if(null == data){
                        //超过2S还没有获取到数据，认为所有生产者线程已经退出，自动退出消费者线程
                        isRunning = false;
                    }else{
                        System.out.println("从队列里获取到数据：" + data + ",并开始消费");
                        //模拟消费
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("消费者线程退出");
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> dataQueue =  new LinkedBlockingQueue<>(10);
        Producer producer1 = new Producer(dataQueue);
        Producer producer2 = new Producer(dataQueue);
        Producer producer3 = new Producer(dataQueue);
        Consumer consumer = new Consumer(dataQueue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);
        try {
            TimeUnit.SECONDS.sleep(10);
            producer1.stop();
            producer2.stop();
            producer3.stop();
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();

    }
}
