package com.shyfay.usual;
import java.io.Serializable;

public class OverloadPriority {
    public static void say(char arg){System.out.println("char");}
    public static void say(int arg){System.out.println("int");}
    public static void say(long arg){ System.out.println("long");}
    public static void say(Character arg){System.out.println("Character");}
    public static void say(Serializable arg){System.out.println("Serializable");}
    public static void say(Object arg){System.out.println("object");}
    public static void say(char... arg){System.out.println("char...");}
    public static void main(String[] args){
        say('a');
    }
    //TODO 这种就是参数没有显示的静态类型
    //TODO 依次注释上面的say方法就会陆续输出char int long Character Serializable obj char...
    //TODO 假设删除了char那个say方法，main在调用say('a')对应的重载方法时找不到char那个方法，这时候发生依次自动类型转换输出int
    //TODO 现在把int方法也删了，JVM会进行两次次自动类型转换，char->int->long
    //TODO 然后把long方法也删了，JVM会进行一次自动封装，'a'被包装成它的封装类型Character
    //TODO 然后把Character方法也删了，JVM在查找重载方法的时候发现参数的装箱之后还是找不到以参数的装箱类为参数定义的重载方法say
    //TODO 时就会检查装箱类实现的接口作为参数的重载方法，因为Character类实现了接口Serializable接口，所以会输出Serializable
    //TODO 继续注释掉Serializable方法，就会查找以参数的父类为参数的重载方法
    //TODO 把object方法也注释掉，这时候只剩下唯一的say方法了，这时会输出char... 可见以可变类型作为参数的方法的重载优先级是最低的

}



