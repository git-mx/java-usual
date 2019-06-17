package com.shyfay.usual.thread;

/**
 * @author mx
 * @since 2019/6/17
 */
public class ThreadMain {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        //线程名称
        System.out.println(main.getName());
        //线程ID
        System.out.println(main.getId());
        //线程优先级
        System.out.println(main.getPriority());
        //线程活动状态
        System.out.println(main.isAlive());
        //是否为守护线程
        System.out.println(main.isDaemon());
        //判断线程是否被中断
        System.out.println(main.isInterrupted());
    }
}
