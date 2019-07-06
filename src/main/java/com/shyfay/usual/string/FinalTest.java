package com.shyfay.usual.string;

/**
 * 常量作为参数传递时，并不会改变常量本身的值，其实传递的是常量的引用，并不是常量本身,
 * 注意看FinalTest和ReferenceTest的区别
 * @author mx
 * @since 2019/7/6
 */
public class FinalTest {

    private static void change(String s1, String s2){
        //让s1指向s2指向的常量"world"
        s1 = s2;
        //在常量池中新建常量"worldworld"
        s2 = s1 + s2;
        System.out.println(s1 + "-" + s2);
    }

    public static void main(String[] args) {
        //直接在main方法栈创建常量s1，即在常量池中新建常量"hello"
        String s1 = "hello";
        //直接在main方法栈创建常量s2，即在常量池中新建常量"world"
        String s2 = "world";
        //将s1和s2的副本作为参数传递
        change(s1, s2);
        //执行后s1仍然指向常量池中的"hello"，s2任然指向常量池中的"world"
        System.out.println(s1 + "-" + s2);
    }
}
