package com.shyfay.usual.thread.threadpool;


/**
 * @author mx
 * @since 2019/7/26
 */
public class MyTask implements Runnable {
    private int taskNum;
    public MyTask(int taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println("正在执行task[" + taskNum + "]");
        try{
            Thread.currentThread().sleep(4000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("task[" + taskNum + "]执行完毕");
    }
}
