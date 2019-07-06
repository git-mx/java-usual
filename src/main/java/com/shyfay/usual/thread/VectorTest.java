package com.shyfay.usual.thread;

import java.util.Vector;

/**
 * 绝对线程安全：绝对线程安全一般是指一段代码是否是绝对线程安全的，例如java.util.Vector类是一个线程
 * 安全的容器，因为它的add,remove等方法都是被同步关键字synchronized关键字修饰的。但是如果在一段代码里，起了两个线程，一个线程
 * 循环往Vector实例对象里循环添加100000个值，另外一个线程不停地执行remove()操作，那么就会报java.lang.ArrayIndexOutOfBoundsException
 * 错误，因为虽然add或者remove方法是线程安全的（因为在add和remove那一瞬间程序能够保证只有一个线程在操作当前容器），但是在这些
 * add和remove操作的瞬间之间的时间间隙其他线程来改变了容器的值，最终的结果也不会是人们想要的，那么我们就称这一段代码不是绝对线程安全的。
 * 相对线程安全：相对线程安全就是我们传统意义上所说的线程安全，相对线程安全是指针对某一个对象在执行特定的原子操作时不会被其他的线程影响。
 * 例如Vector类的add,remove这种原子操作都是线程安全的。
 * 线程兼容：线程兼容是指对象本身不是线程安全的，但是可以在调用端正确地使用同步手段来保证对象在并发环境中可以安全地使用，例如我们经常会用到的
 * redis分布式锁，redis分布式锁对针对的对象比如产品列表，它本身就不是线程安全的，但是加上redis分布式锁以后我们就称这种加了redis分布式锁
 * 的产品列表是线程兼容的
 * 线程对立：线程对立是指无论在调用端是否采用了同步措施，都无法在并发环境中使用的代码，一个线程对立的例子就是Thread类的suspend和resume方法
 * 如果两个线程同时持有一个对象，一个线程尝试去中断线程，另一个线程尝试恢复线程，如果并发进行的话，无论调用时是否进行的同步处理，目标线程都存在
 * 死锁的风险。
 * 如果把两个synchronized(vector)去掉，那么则会抛出异常
 * @author mx
 * @since 2019/6/27
 */
public class VectorTest {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while(true){
            for(int i=0; i<10; i++){
                vector.add(i);
            }
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(vector){
                        for(int i=0; i<vector.size(); i++){
                            //System.out.println(vector.toString());
                            vector.remove(i);
                        }
                    }
                }
            });
            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(vector){
                        for(int i=0; i<vector.size(); i++){
                            System.out.println(vector.get(i));
                        }
                    }
                }
            });
            removeThread.start();
            printThread.start();
            while(Thread.activeCount() > 20){

            }
        }
    }
}
