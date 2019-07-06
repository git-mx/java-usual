package com.shyfay.usual.thread.sync;

/**
 * 该端代码用于演示synchronized作用于static方法时的效果
 * 当synchronized作用于类的某个static方法时或者以synchronized(StaticMethod.class)的方式
 * 修饰代码块时，那么作用于StaticMethod类的所有的实例对象的所有同步方法的线程都将被阻塞，直到当前线程的
 * synchronized方法或代码块被执行完毕。
 * 对于作用在非static方法或者以synchronized(object)的方式作用的代码块而言，只会阻塞那些调用当前对象
 * （即方法的调用者）的所有被synchronized修饰的方法或代码块的线程，直到当前线程执行完当前的synchronized方法或代码块
 * @author mx
 * @since 2019/7/6
 */
public class StaticMethodTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StaticMethod.method1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                StaticMethod.method2();
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run(){
                StaticMethod.method3();
            }
        }).start();
    }
}
