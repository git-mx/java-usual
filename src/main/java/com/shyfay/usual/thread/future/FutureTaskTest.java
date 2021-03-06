package com.shyfay.usual.thread.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * JAVA有三种方式来创建线程，第一种就是继承Thread类，第二种就是实现Runnable接口
 * 第三种就是使用Future和Callable结合实现，
 * JAVA5提供了Future接口来代替Callable接口里call方法的返回值，并且为Future接口提供了一个实现类FutureTask，
 * 这个实现类既实现了Future接口，还实现了Runnable接口，因此可以作为Thread类的target。在Future接口里定义了
 * 几个公共方法来控制它关联的Callable任务。
 * V get()，该方法用于获取Callable里call()方法的返回值，调用这个方法会导致线程阻塞，必须等到子线程结束后才能得到返回值
 * boolean cancel(boolean mayInterruptIfRunning)：试图取消该Future里关联的Callable任务。
 * V get(long timeout, TimeUtil timeUtil)，单位时间内仍然没有获取到返回值则返回超时
 * boolean isDone();
 * boolean isCancelled();
 * 使用FutureTask的步骤如下
 * - 创建Callable接口的实现类，并实现call()方法，然后创建该实现类的实例（从java8开始可以直接使用Lambda表达式创建Callable对象）
 * - 使用FutureTask类来包装Callable对象，该Future对象封装了Callable对象的call()方法的返回值
 * - 使用FutureTask对象作为Thread对象的Target创建线程并启动（因为FutureTask实现了Runnable接口）
 * - 调用FutureTask对象的get方法来获取子线程执行结束后的返回值。
 * 要使程序支持lambda表达式，需要
 * - 1.File -> Project Structure.. -> Modules -> Java Languages 选择8
 * - 2.File -> Settings -> Build -> Complier -> Java Compiler -> Project bytecode version 选择8
 * @author mx
 * @since 2019/6/17
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        Callable<String> callable = new Callable<String>() {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            @Override
            public String call() throws Exception {
                Integer integer;
                for(int i=1; i<10001; i++){
                    integer = random.nextInt(10001);
                    if(integer % i == 3){
                        return Thread.currentThread().getName() + "|" + integer + ":" + i;
                    }
                }
                return null;
            }
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        while(!futureTask.isDone()){
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e){

            }
            System.out.println("可以在这里执行futureTask.cancel();中断futureTask的执行。。。");
        }
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
