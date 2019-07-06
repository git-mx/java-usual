package com.shyfay.usual.thread.future;

/**
 * @author mx
 * @since 2019/7/6
 */
public class XiFanThread extends Thread {
    @Override
    public void run() {
        try{
            Thread.sleep(2000);
            System.out.println("稀饭准备完毕...");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
