package com.shyfay.usual.modifier;

/**
 * static关键字的作用
 * - static方法，static方法被称为静态方法，静态方法不依赖于任何对象就可以进行方法，所以它没有this指针，在静态方法中只能访问类的静态变量和静态方法
 * - static变量，static变量被称为静态变量，静态变量是所有的对象共享，在整个程序运行期间内存中都只有一块内存区域存的是该变量的值，它必须在类被加载时
 *              初始化，即在定义静态变量时就必须初始化变量。而普通变量则是对象私有的，在类被实例化时被初始化内存中有几个对象，就有几个成员变量的副本。
 * - static代码块， static代码块的作用是优化程序的性能，static代码块只会在类被加载时执行一次，其他时候都不会被执行。静态代码块只能定义在类里，不能
 *              定义在方法里。
 * - static不能修饰一般的类，它只能修饰内部类。静态内部类只能访问外部类的静态成员。
 * - import static 静态导入，即导入一个类的所有静态资源（包含静态方法和静态变量）
 *
 * @author mx
 * @since 2019/6/17
 */
import static java.lang.Math.*;
public class StaticTest {
    static {
        System.out.println("AAAAAAAAAAAA");
    }

    public static void main(String[] args) {
        System.out.println("BBBBBBBBBBB");
        //由于使用了静态导入，可以直接使用sin方法，而无需写成Math.sin
        System.out.println(sin(2.2));
    }
}
