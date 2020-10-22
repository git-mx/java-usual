package com.shyfay.usual.apachecommons.lang3;


import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @Notes StopWatch时间监视器，最优雅的计算程序执行时间的方式
 * @Author muxue
 * @Since 8/5/2020
 */
public class StopWatchTest {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTime() + "ms");
        System.out.println(stopWatch.getNanoTime() + "nanos");
    }
}
