package com.shyfay.usual.jvm;

/**
 * @Notes Object类的finalize()方法，有点有点类似于C++的析构函数,
 * 一个类如果覆盖了Object的finalize()且方法体非空，那么JVM在GC时针对这个类的对象时都会
 * 执行这个对象的finalize()方法，这样我们程序员就可以将自己的业务处理逻辑放入到finalzie()方法里
 * 比如一个连接池的连接对象，可以覆盖写finalize方法，在方法体里实现释放其他资源（比如socket和缓冲区等等）的操作
 * 这样当这个连接对象被回收时，JVM会调用这个连接对象的finalize方法完成我们希望做的事情（释放资源）
 * finalize的实现原理：
 * JVM在类加载的时候扫描类的所有方法，如果扫描到一个方法名称为finalize并且返回值为void且方法体非空，那么该类就是一个
 * FinalReference类，当在程序中创建f类的对象时会调用这个类的构造方法，同时也会调用父类的构造方法（为了初始化那些继承自父类的属性）
 * 而所有的类都继承来自Object，所以最终都会调用到Object的空构造方法，JVM在执行Object的空构造方法时，
 * 会判断如果该类是f类，那么则将这个对象放入一个ReferenceQueue队列，并且JVM会启动一个守护线程，去轮询这个
 * ReferenceQueue队列里的元素，如果元素已经没有业务引用与之关联则取出来调用其runFinalizer方法，
 * 这个方法会将f对象从ReferenceQueue里剥离出来
 * 这样下一次GC的时候就可以回收个对象了，同时runFinalizer方法还会做一件事情就是调用对象的finalize方法，
 * 实现我们希望实现的业务逻辑
 * @Author muxue
 * @Since 9/7/2020
 */
public class FinalizeTest {
}
