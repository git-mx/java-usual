package com.shyfay.usual.thread.future.java8;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Notes
 * @Author muxue
 * @Since 8/6/2020
 */
public class CallableTask implements Callable<Integer> {

    Integer value;

    public CallableTask(Integer value){
        super();
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        try {
            if(1 == value){
                TimeUnit.MILLISECONDS.sleep(3000);
            }else if(5 == value){
                TimeUnit.MILLISECONDS.sleep(5000);
            }else{
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            return value;
        } catch(InterruptedException e) {

        }
        return 0;
    }
}
