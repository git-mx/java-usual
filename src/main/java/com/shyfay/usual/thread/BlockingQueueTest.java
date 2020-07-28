package com.shyfay.usual.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Notes 有两个线程，A线程是生产者，B线程是消费则，A线程每200毫秒生产一次，B线程每2秒消费一次
 * 用阻塞队列来实现
 * @Author muxue
 * @Since 7/24/2020
 */
public class BlockingQueueTest {
    //因为每2秒是200毫秒的2倍，因此设置定长的阻塞队列，并且长度设置为10，因为队列满的时候会消费则线程全部消费
    private static BlockingQueue<Integer> integerQueue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        //创建一个定时执行的线程池
        ScheduledExecutorService productExecutor = Executors.newScheduledThreadPool(1);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        //生产者线程，每隔200毫秒执行一次
        productExecutor.scheduleAtFixedRate(() -> {
            //产生一个0到1000的随机数
            int value = random.nextInt(1001);
            //将生产的整数放入阻塞队列
            integerQueue.offer(value);
        }, 0, 200, TimeUnit.MILLISECONDS);
        //消费者线程
        new Thread(() -> {
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println("开始消费");
                    List<Integer> list = new LinkedList<>();
                    //将队列里的元素全部取出并放入指定的容器，非阻塞立刻返回
                    integerQueue.drainTo(list);
                    list.forEach(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
