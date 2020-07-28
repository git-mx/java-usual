package com.shyfay.usual.keyword.kfinal;

/**
 * @Notes final关键字
 * 1.修饰类，那么该类不能被继承
 * 2.修饰方法，那么该方法不能给覆盖或复写
 * 3.修饰成员变量，那么该成员变量的值不能被修改，那么意味着
 *   3.1 如果一个类定义了一个final成员变量，那么不能为该成员变量编写set方法
 *   3.2 如果在定义这个final成员变量的时候给定了一个初始值，那么在构造方法（无论是无参数构造方法或者无参数构造方法）
 *       和其他任何方法里都不能再次为该成员变量进行赋值。
 *   3.3 相反的，如果在定义这个成员变量时如果没有给定初始值，那么该类的所有构造方法都必须为该成员变量赋值
 * 4.非常非常非常重要：final的作用域是对象，而不是类
 * @Author muxue
 * @Since 7/20/2020
 */
public class MainTest {
    private final Integer c;
    MainTest(Integer c){
        this.c = c;
    }

    public static void main(String[] args) {
        MainTest test1 = new MainTest(1);
        MainTest test2 = new MainTest(2);
        System.out.println(test1.c);
        System.out.println(test2.c);
    }
}
