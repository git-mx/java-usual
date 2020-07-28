package com.shyfay.usual.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的作用：在需要使用线程的时候直接创建一个线程，这样实现起来非常方便，但是就会有一个问题：
 * 如果并发的线程数量很多，并且每个线程都执行一个时间很短的任务就结束了，这样频繁的创建线程就会大大降低系统的效率，因为线程的创建和销毁是需要消耗资源的
 * 线程池的作用就是让线程可以复用，就是执行完一个任务后并不销毁，而是可以继续执行其他任务
 * corePoolSize 核心线程池大小，即同时并发执行的线程数，如果来了一个任务，这时任务数小于corePoolSize则直接创建一个线程
 * （或者让预先创建的线程：如果调用了prestartAllCoreThreads()，预先启动corePoolSize个线程或调用了prestartCoreThread()预先启动1个线程）
 * 去执行这个任务，
 * maximumPoolSize 线程池最大线程数，如果任务的数量大于corePoolSize，这时就将多出corePoolSize那一部分任务放到缓存队列中，
 * 如果任务数多于maximumPoolSize，则拒绝执行多出的任务并抛出异常
 * keepAliveTime表示线程没有任务执行时最多保持多久时间会终止，默认情况下只有任务数大于corePoolSize时keepAliveTime才会起作用，直到线程池中的
 * 线程数不大于corePoolSize，但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，
 * keepAliveTime参数也会起作用，直到线程池中的线程数为0
 * unit 参数keepAliveTime的时间单位
 * 在线程中不是直接new Thread来执行任务的，而是使用Worker，其实Worker也是实现了Runnable接口的，另外它还继承了
 * AbstractQueuedSynchronizer，用于实现一个不可重入的锁
 * 解锁的过程。
 * 线程池的设置很久讲究，如果是高并发，任务执行时间短的业务，线程数要设置较少为好，这样可以减少线程上下文切换，提高执行效率，就是尽量让CPU在执行完
 * 一个任务后再去执行另一个任务
 * 如果是并发量不高，但是任务执行时间长的要分两种情况，如果是IO操作较多的业务（IO操作并不占CPU资源），可以把线程数设置大一点，如果是计算量大的
 * （需要CPU资源的），可以把线程数设置小一点，减少线程上下文切换
 * 如果是并发量高，而且又耗时的操作，则应该从架构上去提高性能，而不是从线程池上
 * 缓冲队列BlockingQueue
 * ArrayBlockingQueue(int i)：规定大小的BolockingQueue，构造时必须制定大小，起所含的对象是FIFO顺序排列的，是典型的生产者-消费者模型，
 *   一般的操作是一个线程调用put方法往队列里添加元素，一个线程调用take方法从队列里取出元素，ArrayBlockingQueue有两个Condition，一个是notEmpty一个是notFull。
 *   当队列满时，这时会阻塞调用其put方法的线程，直到队列有空的位置。当队列为空时会阻塞调用take方法的线程，直到队列有至少一个元素
 * LinkedBlockingQueue()或者(int i)：当指定大小时与ArrayBlockingQueue差不多，当不指定大小时队列的最大元素个数是Integer.MAX_VALUE.其元素是FIFO顺序排列的
 * PriorityBlockingQueue()或者(int i):类似于LinkedBlockingQueue，但其所含对象的排序不是FIFO，而是依据对象的自然顺序或者构造函数的Comparator决定。
 *   所以它的元素必须实现Comparable接口
 * SynchronizedQueue()：特殊的BlockingQuque，对其元素的操作必须是put和take交替进行。
 * DelayQueue()：这个队列里的元素都会在元素指定的时间后过期，过期的元素在对队列做take操作的时候会被释放掉，过期时间是调用元素的getDelay()方法
 * @author mx
 * @since 2019/7/26
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
        for(int i=0; i<15; i++){
            MyTask task = new MyTask(i);
            //将任务添加到线程池
            executor.execute(task);
            System.out.println("线程池中的线程数：" + executor.getPoolSize()
                    + "，队列中等待执行的线程数：" + executor.getQueue().size()
                    + "，已经执行完的任务数：" + executor.getCompletedTaskCount()
            );
        }
        executor.shutdown();
    }
}
