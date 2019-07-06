package com.shyfay.usual.thread.future;

/**
 * @author mx
 * @since 2019/7/6
 */
public class NormalThread {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BaoZiThread baoZiThread = new BaoZiThread();
        baoZiThread.start();
        try {
            baoZiThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        XiFanThread xiFanThread = new XiFanThread();
        xiFanThread.start();
        try{
            xiFanThread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("准备早饭总共花掉了" + (System.currentTimeMillis() - start) + "毫秒");
    }
}
