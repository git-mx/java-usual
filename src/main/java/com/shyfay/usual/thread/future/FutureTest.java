package com.shyfay.usual.thread.future;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Notes Future和FutureTask没有本质区别，都可以用于批处理
 * 可以使用isDone来判断Future任务是否执行完毕
 * 使用get阻塞获取Future任务的结果
 * 使用cancal来方法来停止任务的执行
 * @Author muxue
 * @Since 8/5/2020
 */
public class FutureTest {

    private static class CallableTask implements Callable<Integer> {

        private int value;

        public CallableTask(int value){
            super();
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            if(this.value == 1){
                TimeUnit.MILLISECONDS.sleep(1000);
            } else if(this.value == 3){
                TimeUnit.MILLISECONDS.sleep(3000);
            } else if(this.value == 5){
                TimeUnit.MILLISECONDS.sleep(5000);
            }
            return this.value;
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Integer> integers = new ArrayList<>();
        List<Future<Integer>> futures = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i=0; i<10; i++){
            futures.add(executor.submit(new CallableTask(i)));
        }
        Future<Integer> future;
        Integer i;
        while(futures.size() > 0){
            Iterator<Future<Integer>> iterator = futures.iterator();
            while(iterator.hasNext()){
                future = iterator.next();
                if(future.isDone() && !future.isCancelled()){
                    i = future.get();
                    integers.add(i);
                    iterator.remove();
                }else{
                    TimeUnit.MILLISECONDS.sleep(1);
                }
            }
        }
        stopWatch.stop();
        System.out.println("list:" + integers + ", time:" + stopWatch.getTime() + "ms");
    }
}
