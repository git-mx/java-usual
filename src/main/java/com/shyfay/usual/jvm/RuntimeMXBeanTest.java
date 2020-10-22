package com.shyfay.usual.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Notes RuntimeMXBean 用于获取运行时信息
 * 另外ManagementFactory类有许多方法用于获取各种运行时信息，比如关于平台的，线程的，类加载器的
 * ，编译器的，等等等等
 * @Author muxue
 * @Since 8/2/2020
 */
public class RuntimeMXBeanTest {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        //获取当前的进程ID,可以使用jstack查看堆栈信息了 jstack -l xxx
        System.out.println(runtimeMXBean.getName().split("@")[0]);
        //获取当前线程的ID
        System.out.println(Thread.currentThread().getId());
        new Thread(() -> {
            IntStream.rangeClosed(1, 1000).forEach(e -> {
                try {
                    TimeUnit.SECONDS.sleep(e);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + e);
            });
        }).start();
    }
}
