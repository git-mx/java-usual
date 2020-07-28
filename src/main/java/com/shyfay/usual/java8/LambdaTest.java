package com.shyfay.usual.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Notes lambda 表达式比起传统的性能是要低一点的
 * 匿名内部类的实现原理：
 * 会创建一个FileName$1.class的文件（数字不一定是1）
 * lambda表达式的实现原理：
 * 并不会产生一个.class文件
 * 1.lambda表达式被编译器生成当前类的一个私有静态方法
 * 2.在原调用lambda方法的地方编译成一个invokedynamic指令
 * 3.当lambda表达式被JVM执行，也就是碰到2中说到的invokedynamic指令，该指令引导调用
 *   LambdaMetafactory.metafactory()方法，该方法会返回一个CallSite实例
 * 4.CallSit实例中的target对象，也就是直接引用到一个MethodHandle实例，而这个实例会调用到
 *   1中产生的静态方法。
 * @Author muxue
 * @Since 7/27/2020
 */
public class LambdaTest {
    //通俗的写法
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "jack", "mike");
        //匿名内部类，即一个实现了Comparator接口的类
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        //lambda表达式
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println(names);
    }
}
