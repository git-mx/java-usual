package com.shyfay.usual.jvm;

import com.shyfay.usual.Person;
import com.shyfay.usual.User;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Notes java对象可以分成两种类型：值类型和引用类型
 * 值类型有：byte short int long float double char boolean
 * 引用类型：除了值类型以外的所有对象都是引用类型（数组，字符串，类，接口等等）
 * 字符串比较特殊：它专门有一个字符串常量池用于存储常量字符串（在常量池内的字符串属于值类型比如String str = "AAAA"这里的str就是值类型）
 * 值类型是直接存放在栈内存里的，栈内存地址就直接存放的对象的值
 * 引用类型分为两部分：对象的引用和对象的值，对象的引用存放在栈内存里，对象的值存放在堆内存里，
 * 栈内存存放的是引用对象的值（引用也一个是个对象）的地址，堆内存存放对象的值
 * 方法区：跟堆一样，被所有的线程共享，方法区包含所有的class和static变量。方法区中包含的的都是整个
 *       程序中永远唯一的变量，如class和static对象。在Java8之前，HotSpot虚拟机中将GC分带搜集
 *       扩展到了方法区，使用永久代来实现了方法区，这个区域的内存回收主要针对常量池的回收和对类型
 *       的卸载。而在JAVA8中，已经没有了永久代，而是将方法区直接放在一个与堆不连的本地内存区域，
 *       称为元空间，理论上来说只要物理内存有多大，元空间就有多大
 * java有四种引用类型 强引用 软引用 弱引用和虚引用
 * Reference<T> 有四个实现类 FinalReference SoftReference WeakReference PlantomReference
 *              FinalReference并不是强引用
 * - 强引用：只要强引用还在，那么GC永远不会回收引用指向的对象，平时我们写的Person person = new Person("ShyFay", 18);
 *          这里的person就是是一个强引用，或者String str = new String("aaaa"); str也是一个强引用
 * -软引用：是用来描述一些还有用但是非必须的对象，对于软引用对象，如果内存充足时GC不会回收软引用对象
 *        ，如果内存不够用了，软引用对象将被GC回收。它的实现原理是为对象创建一个创建一个软引用对象
 *        （这时这个对象有两个引用与之关联，一个是业务代码里的引用，一个就是软引用）。软引用并不会对对象
 *        本身的GC造成影响：首先如果业务代码里的引用不存在了，这时如果内存充足GC并不会立即回收这个对象，此时的对象
 *        处于软可达（软引用还指向它），如果内存吃紧，GC过程就会回收这个对象（但是软引用对象还存在）。
 *        软引用对象需要程序员手动回收，可以让软引用一个ReferenceQueue，在构造软引用的时候将之
 *        与ReferenceQueue关联之后，如果软引用对象指向的对象被回收了，那么这个软引用对象就会被放入
 *        这个ReferenceQueue，然后程序员可以写一个守护线程去轮询这个ReferenceQueue释放这些
 *        软应用对象占用的内存。弱引用可以用于实现一个高速缓存
 * -弱引用：只要发生GC，弱引用指向的对象将被回收
 * -虚引用：虚引用完全不影响对象的生命周期，一个对象与虚引用关联，则跟没有引用关联一样，它必须和ReferenceQueue一起使用
 *        它的作用一般在于跟踪垃圾回收过程。当GC器准备回收一个对象时，如果发现它还有虚引用，就会在销毁这个对象之前
 *        ，将这个对象关联的虚引用加入到引用队列，因此我们可以通过判断引用队列中是否已经加入了虚引用来了解
 *        引用对象是否将要被垃圾回收
 * WeakHashMap 弱键Map 它典型的应用场景就是保存不受控制生命周期的对象的元数据，例如用于存储各个现成的元数据
 *        当线程销毁时，将从WeakHashMap中移除：WeakHashMap<Thread, SomeMetaData>
 * @Author muxue
 * @Since 9/7/2020
 */
public class ReferenceTest {
    public static void main(String[] args) {
        Person p1 = new Person("p1", 1);
        Person p2 = new Person("p2", 2);
        AtomicReference<Person> ar = new AtomicReference(p1);
        System.out.println(ar.get().getAge());
        p1.setAge(3);
        //比较两个对象在内存中的地址是否相等，如果不等则将引用指向新的对象，并且保证操作的原子性，保证线程安全
        ar.compareAndSet(p1, p2);
        Person p3 = ar.get();
        System.out.println(p3.getAge());
    }
}
