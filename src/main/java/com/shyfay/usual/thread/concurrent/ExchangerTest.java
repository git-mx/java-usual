package com.shyfay.usual.thread.concurrent;

import java.util.List;
import java.util.concurrent.*;

/**
 * 本例用于演示concurrent包的Exchanger交换机
 * 交换机Exchanger表示一种两个线程可以进行相互交换对象的汇合点
 * 本例用于演示生产者不断的生产Fat，而消费者不断的消费Fat，生产者一填满列表就阻塞等到消费者来取走，消费者每取一次就拿自己的空列表
 * 取换取生产者填满的满列表
 * @author mx
 * @since 2019/8/21
 */
public class ExchangerTest {
//    private static class ExchangerRunnable implements Runnable {
//        Exchanger exchanger = null;
//        Object object = null;
//        public ExchangerRunnable(Exchanger exchanger, Object object){
//            this.exchanger = exchanger;
//            this.object = object;
//        }
//
//        @Override
//        public void run(){
//            Object previous = this.object;
//            try{
//                this.object = this.exchanger.exchange(this.object);
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
//        }
//    }
//
//    public static void main(String[] args) {
//        Exchanger exchanger = new Exchanger();
//        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
//        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");
//        new Thread(exchangerRunnable1).start();
//        new Thread(exchangerRunnable2).start();
//    }

    private static final int size = 10; //数组的长度
    private static final int delay = 5; //秒
    static class Fat{
        private volatile double d;
        private static int counter = 1;
        private final int id = counter++;
        public Fat(){
            //执行一段耗时的操作
            for(int i=0; i<10000; i++){
                d += (Math.PI + Math.E) / (double)i;
            }
        }
        public void print() {
            System.out.println(this);
        }

        public String toString(){ return "Fat id=" + id; }
    }

    static class ExchangerProducer implements Runnable {
        private List<Fat> holder;
        private Exchanger<List<Fat>> exchanger;
        public ExchangerProducer(List<Fat> holder, Exchanger<List<Fat>> exchanger){
            this.holder = holder;
            this.exchanger = exchanger;
        }

        @Override
        public void run(){
            try{
                while(!Thread.interrupted()){
                    //填充列表
                    for(int i=0; i<size; i++){
                        holder.add(new Fat());
                    }
                    //等待交换
                    holder = exchanger.exchange(holder);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Producer stopped...");
        }
    }

    static class ExchangerConsumer implements Runnable{
        private List<Fat> holder;
        private Exchanger<List<Fat>> exchanger;
        private volatile Fat value;
        private static int num = 0;
        public ExchangerConsumer(List<Fat> holder, Exchanger<List<Fat>> exchanger){
            this.holder = holder;
            this.exchanger = exchanger;
        }
        @Override
        public void run(){
            try{
                while(!Thread.interrupted()){
                    //等待交换
                    holder = exchanger.exchange(holder);
                    //读取列表并移除元素
                    for(Fat x : holder){
                        num ++;
                        value = x;
                        //在循环内删除元素，这对于CopyOnWriteArrayList是没有问题的
                        holder.remove(x);
                    }
                    if(num%10000 == 0){
                        System.out.println("Exchanged count = " + num);
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Consumer stopped, final value:" + value);
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Fat> producerList = new CopyOnWriteArrayList<>();
        List<Fat> consumerList = new CopyOnWriteArrayList<>();
        Exchanger<List<Fat>> exchanger = new Exchanger<>();
        exec.execute(new ExchangerProducer(producerList, exchanger));
        exec.execute(new ExchangerConsumer(consumerList, exchanger));
//        try{
//            TimeUnit.SECONDS.sleep(delay);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//        exec.shutdownNow();
    }
}
