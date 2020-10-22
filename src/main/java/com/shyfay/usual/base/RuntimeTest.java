package com.shyfay.usual.base;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Notes Runtime类封装了运行时环境，应用程序中是不允许新建RunTime类的实例的
 * 一旦JVM启动，JAVA程序中就维护了一个Runtime类的实例
 * 只能通过Runtime.getRuntime()方法获得这个实例的引用
 * exit(0/1);按照指定的状态关闭当前JVM
 * exec("xxx.exe");运行某个程序
 * gc();运行垃圾回收器
 * halt(int status); 强行终止目前正在运行的虚拟机
 * load和loadLibrary 用于加载第三方的类库，在JNI编程中会用到
 * @Author muxue
 * @Since 8/1/2020
 */
public class RuntimeTest {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("处理器数量：" + runtime.availableProcessors());
        System.out.println("空闲内存：" + runtime.freeMemory() + "byte");
        System.out.println("总内存数：" + runtime.totalMemory() + "byte");
        System.out.println("可用最大内存数：" + runtime.maxMemory());
        IntStream.rangeClosed(1, 3).forEach(e -> {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + e);
                TimeUnit.MILLISECONDS.sleep(e * 100);
                if(2 == e){
                    //终止虚拟机
                    runtime.exit(0);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
    }
}
