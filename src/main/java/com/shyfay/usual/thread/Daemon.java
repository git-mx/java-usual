package com.shyfay.usual.thread;

/**
 * 守护线程
 * 在java中有两类线程：User Thread（用户线程）、Daemon Thread（守护线程）
 * 任何一个守护线程都是JVM中所有非守护线程的保姆。
 * 只要当JVM中还有一个用户线程在执行时，那么所有的守护线程就都处于运行状态，
 * 只有当JVM中所有的用户线程都执行完毕时，守护线程才会随着JVM的关闭一起结束。
 * Daemon的作用是为其他线程的运行提供服务，守护线程最典型的应用就是GC。
 * 值得注意的是守护用户也可以创建守护线程，而且守护线程不一定会执行完毕，只要在所有的用户线程结束工作了之后，所有的守护线程都会
 * 立刻结束，不管线程自身定义的业务逻辑有没有执行完毕。所以一般不会把与具体业务相关的逻辑代码放入到守护线程里。
 * 下面的例子就是thread2就是一个守护线程，在thread1执行完之后（这表示所有的用户线程都已经执行完毕了），thread2会立刻结束工作
 * 而不会等待for循环执行完毕才结束。
 * 一个典型的守护线程就是服务器端的Servlet容器。实际上就是一个守护线程
 * 在一个服务器端程序中如果用户只指定了一个Servlet容器（可以指定多个），那么全局就只有一个Servlet容器的对象。该对象接受来自客户端的请求
 * 每当接收到一个来自客户端的请求时，Servlet守护线程就会从自己的用户线程池取出一个线程来执行当前客户端的请求，这就是为什么在我们一般的JAVA
 * WEB程序中每一个请求都是一个独立的线程了，Servlet容器的用户线程池大小可以通过Tomcat的server.xml的<Connector>元素来指定
 *
 * @author mx
 * @since 2019/6/17
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    System.out.println("用户线程第" + i + "次输出...");
                    try{
                        Thread.sleep(7);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    System.out.println("守护线程第" + i + "次输出....");
                    try{
                        Thread.sleep(7);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.setDaemon(true);
        thread2.start();
        thread1.start();
    }
}
