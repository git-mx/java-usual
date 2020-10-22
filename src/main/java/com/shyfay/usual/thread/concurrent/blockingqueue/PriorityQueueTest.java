package com.shyfay.usual.thread.concurrent.blockingqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Notes 比较优先级队列，即在出队列的时候根据自然顺序或者程序员自定义的比较器，进行排序出队列
 * @Author muxue
 * @Since 8/29/2020
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.add("aaaa");
        priorityQueue.add("bbbb");
        priorityQueue.add("cccc");
        priorityQueue.add("dddd");
        priorityQueue.add("eeee");
        while(priorityQueue.size() != 0){
            System.out.println(priorityQueue.remove());
        }
    }
}
