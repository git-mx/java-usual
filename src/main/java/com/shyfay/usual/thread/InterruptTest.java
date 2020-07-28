package com.shyfay.usual.thread;

/**
 * @Notes JAVA线程的interrupt() interrupted() isInterrupted()方法
 * interrupt() 如果线程A里执行线程B的interrupt()方法则会中断B线程执行，去接着去执行A线程里的代码，A线程的任务
 *             执行完毕之后接着执行B线程剩下的工作，即使A线程里面含有return语句，A线程也会继续执行
 *             如果在A线程里执行B.interrupt()方法的时候B线程已经执行完毕了，那么相当于B.interrupt()
 *             这句代码没有被执行过（也就是说当调用B.isInterrupted()返回false）
 * interrupted() 返回当前线程的中断状态，并且将当前线程的中断状态清除
 * isInterrupted() 返回当前线程的中断状态
 * @Author muxue
 * @Since 7/22/2020
 */
public class InterruptTest {
    private static final String str = "==========================================================================================================================================================";
    public static void main(String[] args) {
        try {
            MyThread14 thread = new MyThread14();
            thread.start();
            Thread.sleep(1000); //此方法代表 让当前线程休眠 1 秒，即表示使 main线程休眠 1秒
            thread.interrupt();
            //if(thread.interrupted()){
            if(thread.isInterrupted()){
                //thread.interrupted() 这句代码是返回调用thread.interrupted()这句代码的线程
                //的中断状态，并将调用thread.interrupted()这句代码的线程的中断状态清除
                boolean b = thread.interrupted();
                System.out.println(str + b);
                System.out.println("AAAAA");
                //这里只是main主线程返回了，但是子线程myThread还是会继续执行
                //return;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("----------end-----------");
    }

    static class MyThread14 extends Thread {
        public void run() {
            for (int i = 0; i < 500000; i++) {
                System.out.println("i = " + i);
            }
        }
    }
}
