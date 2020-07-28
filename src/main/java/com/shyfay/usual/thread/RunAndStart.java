package com.shyfay.usual.thread;

/**
 * @Notes 本例用于演示Thread的run方法和start方法的区别
 * start方法是让线程处于就绪状态，等到CPU执行时间片就开始运行线程体（线程内定义的run方法）
 * run方法只是一个单纯的方法而已，因此多次调用同一个对象的run方法等同于程序顺序执行
 * @Author muxue
 * @Since 7/24/2020
 */
public class RunAndStart {
    public static void main(String[] args) {
        //每一个对象InnerRunnable互不影响，因此顺序输出
//        new Thread(new InnerRunnable()).start();
//        new Thread(new InnerRunnable()).start();
//        new Thread(new InnerRunnable()).start();
        //同一个对象，调用run方法，一下的写法等同于调用了三次runable.run()
        //同一个Thrad的run方法多次运行的情况下，也是并行执行的，不会有线程安全问题
        InnerRunnable runnable = new InnerRunnable();
//        new Thread(runnable).run();
//        new Thread(runnable).run();
//        new Thread(runnable).run();
        //最后两次的值不确定，因为三个线程是并行执行的
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

    }

    private static class InnerRunnable implements Runnable {
        int count = 1;
        @Override
        public void run() {
            while(true){
                System.out.println(count);
                if(++count > 20){
                    break;
                }
            }
        }
    }

}
