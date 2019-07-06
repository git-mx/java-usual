package com.shyfay.usual.thread;

/**
 * JAVA线程状态，JAVA线程有5种状态：新建，运行，等待，阻塞，结束。阻塞和等待的区别是：
 * 阻塞是等待其持有的排它锁被另外的线程释放时线程处于阻塞状态，处于阻塞状态的线程在其他线程释放这个排它锁的时候获得这个排它锁，
 * 从而从阻塞状态切换到运行状态，线程的阻塞状态是程序自身觉得什么时候将线程阻塞或者激活的。而线程等待则是由程序员通过代码执行
 * 线程的wait()方法时将线程手动设置成等待状态的，程序员也可以通过notify()方法来唤醒正在处于等待状态的某个线程
 * 线程安全：当多个对象访问一个对象时，如果不用考虑这些线程在运行时环境下的调度和交替执行，也不需要额外的同步，或者在调用方进行任何
 * 其他的协调操作，调用这个对象的行为都能得到正确的结果，那么这个对象就是线程安全的。
 * 我们将JAVA语言中的操作共享数据类型分为5类（这里指的对象是指一个变量或者一个方法，或者一段代码）：
 * 不可变、绝对线程安全、相对线程安全、线程兼容和线程对立。不可变：使用final关键字修饰的对象就是不可变对象，不可变对象是线程安全的，
 * 因为final修饰的对象其值不能被改变，String类就是不可变对象，它的substing，replace，concat等都不会对自身的值产生影响
 * 而是只会返回一个新的字符串。绝对线程安全：绝对线程安全一般是指一段代码是否是绝对线程安全的，例如java.util.Vector类是一个线程
 * 安全的容器，因为它的add,remove等方法都是被同步关键字synchronized关键字修饰的。但是如果在一段代码里，起了两个线程，一个线程
 * 循环往Vector实例对象里循环添加100000个值，另外一个线程不停地执行remove()操作，那么就会报java.lang.ArrayIndexOutOfBoundsException
 * 错误，因为虽然add或者remove方法是线程安全的（因为在add和remove那一瞬间程序能够保证只有一个线程在操作当前容器），但是在这些
 * add和remove操作的瞬间之间的时间间隙其他线程来改变了容器的值，最终的结果也不会是人们想要的，那么我们就称这一段代码不是绝对线程安全的。
 *
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
