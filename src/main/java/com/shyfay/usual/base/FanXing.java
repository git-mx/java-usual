package com.shyfay.usual.base;

import java.util.List;

/**
 * 本例用于欺骗虚拟机
 * 两个fun1方法是无法通过编译的，因为Java的泛型是伪泛型，即类型擦除。在编译期List<Integer>
 * 和List<String>编译器认为这两个类型是同一种类型，
 * 两个fun2方法也是无法通过编译的，因为在Java里，方法的返回值并不参与方法的重载
 * 但是在jdk6中两个fun3是可以同时存在的
 * 泛型擦除：编译器可以存在真正的泛型，但是当类被加载到JVM之后泛型就被擦除了，替换成原来的类型
 * 比如List<String>在编译期可能编译成为List<T>但是在运行时，实际上的字节码是这样的：
 * List<Object>然后进行强制类型转换，JVM在泛型擦除时总是将泛型参数变成顶级父类（即将类加载到），
 * 然后通过强制类型转换成具体的类型，定义一个这样的文件public class Inner<T> {private T t;}
 * 然后利用javap -verbose反编译查看字节码就知道t实际上是一个java.lang.Object;
 *
 * @author mx
 * @since 2019/6/15
 */
public class FanXing {
//    public void fun1(List<Integer> integerList){
//        System.out.println("aaaa");
//    }
//
//    public void fun1(List<String> stringList){
//        System.out.println("bbbb");
//    }

//    public String fun2(List<Integer> integerList){
//        return "1111";
//    }
//
//    public Integer fun2(List<Integer> integerList){
//        return 1111;
//    }
//    public Integer fun3(List<Integer> integerList){
//        return 1;
//    }
//
//    public String fun3(List<String> stringList){
//        return "1111";
//    }
}
