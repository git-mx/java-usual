package com.shyfay.usual.thread;

/**
 * @author mx
 * @since 2019/6/17
 */
public class MyRunnable implements Runnable {
    public void run() {
        for(int i=0; i<100000; i++){
            System.out.println("BBBBBBBBBB");
        }
    }
}
