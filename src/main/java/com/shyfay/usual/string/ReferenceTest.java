package com.shyfay.usual.string;

/**
 * 引用作为参数传递，并不会改变引用的值
 * @author mx
 * @since 2019/7/6
 */
public class ReferenceTest {

    private static void change(StringBuilder sb1, StringBuilder sb2){
        //让引用sb1指向引用sb2指向的sb2对象内存地址，注意这里只改变了引用sb1的指向，没有改变sb1指向的那块堆内存地址（BBBB）的值
        //这时外部main方法的sb1引用任然指向AAAA，sb2引用任然指向BBBB
        sb1 = sb2;
        //修改sb2指向的sb2对象的内存地址（BBBB）的值
        sb2.append(sb1);
        System.out.println("EEEE:" + sb1 + "----" + sb2);
    }
    public static void main(String[] args) {
        //在main方法栈创建引用sb1，在堆内存创建sb1对象，让sb1指向sb1对象所在的堆内存区域AAAA
        StringBuilder sb1 = new StringBuilder("hello");
        //在main方法栈创建引用sb2，在堆内存创建sb2对象，让sb1指向sb2对象所在的堆内存区域BBBB
        StringBuilder sb2 = new StringBuilder("world");
        //用引用sb1和引用sb2作为参数调用change方法
        change(sb1, sb2);
        //这时sb1任然指向AAAA,sb2任然指向BBBB，而AAAA的值还没改变，但是BBBB的值已经改变了。
        System.out.println("FFFF:" + sb1 + "----" + sb2);
    }
}
