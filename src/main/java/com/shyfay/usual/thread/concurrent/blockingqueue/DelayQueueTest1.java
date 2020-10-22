package com.shyfay.usual.thread.concurrent.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Notes 事实证明，这玩意并不一定是非常准时的
 * @Author muxue
 * @Since 8/29/2020
 */
public class DelayQueueTest1 {
    public static void main(String[] args) {
        DelayQueueTest.DelayedItem<String> item = new DelayQueueTest.DelayedItem<>("AAA", 3000);
        DelayQueue<DelayQueueTest.DelayedItem> delayQueue = new DelayQueue<>();
        System.out.println(System.currentTimeMillis());
        delayQueue.put(item);
        while(true){
            DelayQueueTest.DelayedItem item1 = delayQueue.poll();
            if(null != item1){
                System.out.println(System.currentTimeMillis());
            }
        }
    }
}
