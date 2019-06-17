package com.shyfay.usual;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic{
    public static AtomicInteger RACE = new AtomicInteger(0);
    public static void increase(){
        RACE.incrementAndGet();
    }
    public static int THREAD_COUNT = 20;
    public static void main(String[] args){
        Thread[] threads = new Thread[THREAD_COUNT];
        for(int i=0; i<THREAD_COUNT; i++){
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for(int j=0; j<10000; j++){
                        increase();
                    }
                }
            });
        }
        for(int i=0; i<THREAD_COUNT; i++){
            threads[i].start();
        }
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(RACE);
    }
}