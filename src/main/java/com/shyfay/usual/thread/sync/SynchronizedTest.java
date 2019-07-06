package com.shyfay.usual.thread.sync;

/**
 * 在JVM中，对象在内存中的布局分为三块区域：对象头数据区域、实例变量数据区与和填充数据区域。
 * 对象头数据区域：HotSpot虚拟机中如果是普通对象那么对象头会占用8个字节，如果是数组对象则会占用12个字节
 *  -4个字节用于存储对象自身的运行时数据，如哈希码（HashCode）、GC分带年龄、锁状态标识、线程持有的锁、偏向线程ID、偏向时间戳等等
 *  -4个字节用于存储类型指针，即对象指向它的类的元数据指针，JVM就是通过这4个字节的数据来确定对象是哪个类的实例。
 *  -对于数组对象而言，还有4个字节用于存储数组的长度。对于JVM而言，普通对象可以通过其类的元数据信息确定对象的大小，
 *   例如一个类拥有一个Integer类型的成员变量和一个Long类型的成员变量。那么该类对象的大小就是一个Integer类型的大小（32位）+
 *   Long类型的大小（64位）+其他一些对象需要的内存空间，其他一些对象需要的内存空间针对特定的虚拟机而言它的大小是确定的，从而虚拟机就能
 *   计算出一个普通对象的内存大小了。
 *   但是对于数据而言JVM是没有办法知道其大小的，所以只能在创建数组对象时告知JVM这个数组有多大，这4个字节就是用于存储数组大小的
 * 实例变量数据区域：用于存放类的成员变量信息和这些成员变量的值
 * 填充数据区域：由于HotSpot虚拟机要求对象的起始地址必须是8字节的整数倍，所以只要对象的起始地址不是8字节的整数倍都会被JVM自动填充。
 * 对于synchronized关键字作用代码段时的底层实现是：
 * 每一个java实例对象都有一个monitor对象（没有直接体现在java代码里），这个monitor对象有几个重要的字段：
 * _count 用于存储当前锁定的数量，偏移锁和重入锁就是基于该字段来实现的
 * _owner 存储当前持有自己（monitor）的线程的指针，即表示当前哪个线程拥有锁
 * _WaitSet 已经获得了该锁的线程，但是执行了该monitor对应的java对象的wait方法的那些线程的集合
 * _EntryList 从未获得过该锁，且正在排队获得该锁的线程集合
 * 动态过程是：线程先进入_EntryList集合里，然后判断当前monitor的_owner是不是为null，如果为null把monitor的_owner字段设置成自己，
 * 并将_count+1，如果线程顺利执行完毕则使得_count-1，如果在线程执行中遇到object.wait()方法则将monitor的_owner设置成null，并且
 * 自己进入到_WaitSet集合，注意此时并不会使_count-1。在主线程执行object.notify()方法的时候将monitor的_owner设置成为_WaitSet集合里面的第一个
 * 线程，这是这个线程就获得了锁继续执行，执行完毕后_count-1.所以在主线程执行object.notify()方法时，发现_WaitSet集合里还有元素但是
 * monitor的_count值为0时或者_WaitSet集合里没有元素了，可是monitor的_count!=0，
 * 则认为程序出了错，抛出IllegalMonitorStateException异常。
 * 在字节码中对应了monitorenter指令和monitorexit指令
 * 对于synchronized关键字作用于方法时的底层实现是：
 * 通过方法在方法区标志ACC_SYNCHRONIZED来判断方法是否是一个同步方法，在知道这个方法是一个同步方法后创建一个monitor进行上面的同步过程。
 * @author mx
 * @since 2019/7/6
 */
public class SynchronizedTest {
    private Object object = new Object();
    private Integer integer = 1;
    //表示其他线程不能再执行当前实例的所有被synchronized方法或代码段，这和修饰普通方法是一样的
    public void add1(){
        synchronized(object){
            integer++;
        }
    }
    //表示其他线程不能再执行SynchronizedTest类的所有实例的被synchronized修饰的方法或代码段，这和修饰静态方法是一样的
    public void add2(){
        synchronized(SynchronizedTest.class){
            integer++;
        }
    }
}
