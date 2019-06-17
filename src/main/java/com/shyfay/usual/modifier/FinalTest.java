package com.shyfay.usual.modifier;

/**
 * final关键字的作用
 * 	- 用于修饰类时防止类被继承
 * 	- 用于修饰方法时，防止方法被覆盖
 * 	- 用于修饰变量时，如果是基本数据类型变量则一旦被初始化其值就不能再修改，如果是引用类型的变量则一旦被初始化之后不能再指向其他实例。
 * 	  但是被修饰的引用变量的成员变量的值是可以被修改的
 * 	  当final修饰基础数据类型的变量或者String变量时，如果变量被初始化了，那么在编译期时程序会将在使用到final修饰的变量直接替换成
 * 	  变量的值，所以String strd = strb + 2;就相当于String strd = "hello2";而由于是String直接赋值的变量（不是String abc = new String("abc"）
 * 	  是存在栈区的,栈区分配变量如果给定值的变量已经存在当前线程的栈区时，不会再重新分配空间，而是将当前变量的地址指向已经定义的同值变量的内存地址。
 * 	  而==判断的是两个变量的内存入口地址是否是一样的。第二个判断之所以为false，那是因为JVM在编译期无法确定stre的值，Sting类型比较特殊，它不想基本数据类型
 * 	  如Integer可以在编译期将变量直接替换成变量的值，意思就是在程序编译期对于stre = strc + 2;这个语句来说strc的值是未知的，必须得在运行期才能确定
 * 	  strc的值。
 * @author mx
 * @since 2019/6/17
 */
public class FinalTest {
    public static void main(String[] args) {
        String stra = "hello2";
        final String strb = "hello";
        String strc = "hello";
        String strd = strb + 2;
        String stre = strc + 2;
        System.out.println(stra == strd);//true
        System.out.println(stra == stre);//false
    }
}
