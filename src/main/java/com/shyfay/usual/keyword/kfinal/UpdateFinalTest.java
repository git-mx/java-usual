package com.shyfay.usual.keyword.kfinal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Notes 使用Java反射来改变final变量的值
 * 1.为什么通过反射能够改变final变量的值
 * field.getModifiers()&~Modifier.FINAL这句话的意思就是去掉属性的final修饰符。
 * 其实java的访问权限信息都是以2的N次幂来作为表示的，具体的都在java.lang.reflect.Modifier这个类里面
 * 当我们利用Java的反射机制把成员变量的final关键字干掉之后，那么再通过反射的拿到的对象就可以改变这个值了
 * 其实这整个过程可以模拟称为java拿到字节码文件并修改字节码文件，用这个新的字节码文件构建了一个对象，并且
 * 把这个对象的str2属性的值设置成了"ghi"。从而打印除了新对象的这个属性值
 * 2.为什么最终打印的str2这个字符串的值还是没变？
 * 这是因为JVM的内联优化导致的，即如果一个成员变量被定义成final的，那么在编译期间就直接把这个成员变量的引用
 * 替换成了之际的值，所以我们在运行时再通过反射去无论怎么操作这个对象，这个对象的这个成员变量的值都无法被改变
 * @Author muxue
 * @Since 7/20/2020
 */
public class UpdateFinalTest {
    private static final String str = "abc";
    private final String str2 = "def";

    public void fun() throws Exception {
        System.out.println(str);
        System.out.println(str2);
        Field field = UpdateFinalTest.class.getDeclaredField("str2");
        field.setAccessible(true);
        Field modifiersField = field.getClass().getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(this, "ghi");
        System.out.println(str2);
        System.out.println(field.get(this));
    }

    public static void main(String[] args) throws Exception {
        UpdateFinalTest test = new UpdateFinalTest();
        test.fun();
    }
}
