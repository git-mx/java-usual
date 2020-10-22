package com.shyfay.usual.thread.sync;

import java.util.concurrent.TimeUnit;

/**
 * @Notes 虚假唤醒 spurious weakup
 * JAVA的 synchronized 的DOC的明确指出，在使用wait()让线程进入等待状态时，会存在虚假唤醒的可能
 * 虚假唤醒是指：处于等待状态的线程可以在不被通知、中断或者超时的情况下被唤醒
 * @Author muxue
 * @Since 8/17/2020
 */
public class SpuriousWeakupTest {
    //店员类，负责进货和销售
    private static class Saler {
        //TOTAL表示门店最大可以容纳的存货数量
        private static final int TOTAL = 1;//设置为1为了放大问题
        private int num = 0;//门店当前的存货数量
        //进货，每次都进货一个
        public synchronized void get(){
            if(num >= TOTAL){
                System.out.println("库存已满，无法进货");
                //等待卖出后唤醒
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                //进货成功后唤醒等待的出售线程，告知可以出售
                System.out.println(Thread.currentThread().getName() + ":" + (num++));
                this.notifyAll();
            }
        }

        //出售，每次只能卖出一个
        public synchronized void sale(){
            if(num <= 0){
                System.out.println("没有库存，无法卖出");
                //等待进货后唤醒
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                //成功出售后，唤醒进货的线程，告知可以进货
                System.out.println(Thread.currentThread().getName() + ":" + (num--));
                //如果写这里，会导致程序一直无法退出
                //this.notifyAll();
            }
            this.notifyAll();

        }
    }

    //生产者 可以有很多生产者供货给门店
    private static class Producer implements Runnable {
        private Saler saler;

        public Producer(Saler saler){
            super();
            this.saler = saler;
        }

        @Override
        public void run() {
            for(int i=0; i<20; i++){
                try {
                    //放大问题
                    TimeUnit.MILLISECONDS.sleep(2);
                    saler.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //消费者：可以有很多消费则找店员买货
    private static class Consumer implements Runnable{

        private Saler saler;

        public Consumer(Saler saler){
            super();
            this.saler = saler;
        }


        @Override
        public void run() {
            for(int i=0; i<20; i++){
                saler.sale();
            }
        }
    }

    public static void main(String[] args) {
        Saler saler = new Saler();
        Producer producer = new Producer(saler);
        Producer producer1 = new Producer(saler);
        Consumer consumer = new Consumer(saler);
        Consumer consumer1 = new Consumer(saler);
        new Thread(producer, "producer1").start();
        new Thread(consumer, "consumer1").start();
        new Thread(producer1, "producer1").start();
        new Thread(consumer1, "consumer2").start();
    }
}
