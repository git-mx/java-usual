package com.shyfay.usual.thread.future;

/**
 * 准备包子的线程
 * @author mx
 * @since 2019/7/6
 */
public class BaoZiThread extends Thread {
    @Override
    public void run() {
        try{
            Thread.sleep(3000);
            System.out.println("包子准备完毕...");
        }catch(InterruptedException e){
            System.out.println("thread has been interrupted...");
        }
    }
}
