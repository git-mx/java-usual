package com.shyfay.usual.thread.concurrent.blockingqueue;

/**
 * @Notes current包下新增的BlockingQueue阻塞队列，它是一个接口，继承自Queue接口，
 * 而Queue接口继承自Collection接口，队列中的元素都遵循FIFO规则
 * 核心方法：
 * add(e) 往队列里添加元素时，如果队列满则直接抛出异常
 * offer(e) 往队列里添加元素时，如果成功返回true，否则返回false，立即返回
 * offer(e, timeout, timeunit) 往队列里添加元素时等待指定的时间，如果超时任然未成功返回false
 * put(e) 往队列里添加元素时，如果队列满，则一直阻塞，直到队列腾出空位
 * remove 从队列里取出队首的元素，如果队列空则抛出异常
 * poll(time, timeunit) 从队列里阻塞获取队首元素，直到超时返回失败
 * take() 一直阻塞获取队首元素
 * drainTo() 一次性从队列获取所有元素放入到一个Collection集合里
 * BlockingQueue有5种实现：
 * ArrayBlockingQueue
 * LinkedBlockingQueue
 * SynchronousQueue
 * DelayQueue
 * ProorityBlockingQueue
 * @Author muxue
 * @Since 8/29/2020
 */
public class BlockingQueueTest {
}
