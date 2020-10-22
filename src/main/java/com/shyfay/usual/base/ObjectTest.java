package com.shyfay.usual.base;

import com.shyfay.usual.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @Notes java的Object类
 * @Author muxue
 * @Since 8/1/2020
 */
public class ObjectTest {
    //JAVA所有的类有默认继承自Object类
    //Object类有一个hashCode()方法
    //1.对象的hashCode,一般是通过将该对象的内部地址转换成一个整数来实现
    //2.当一个类没有重写Object类的hashCode()方法时，它的hashCode和System.identityHashCode
    //  是一致的，
    //3.当一个类重写了Object类的hashCode方法时，它的hashCode则由重写的实现逻辑决定，
    //  而System.identityHashCode()则不受重写覆盖的影响。所以即使重写了hashCode方法
    //  每个对象的identityHashCode值也是唯一的
    //4.当null调用hashCode方法时会抛出空指针异常，而System.identity(null) = 0;

    //对象的hashCode是将对象的值根据一定的算法（哈希算法）计算得到的
    //所以两个相同类型的对象，只要他们的值相等，那么他们的hash值（object.hashCode()）一定相等
    //HashMap的key的hash值就是这个key对象的hash值，它是一个整数
    //用这个值对哈希表的长度取模就得到这个元素在hash表中的数组下标
    //equals方法和hashCode方法完全是两码事，他俩没有任何关系，不过我们在定义一个类的时候
    //如果重写了equals方法，那么最好也把hashCode方法重写了，这样做的目的是为了保持和
    //equals方法的步调保持一直，假如我们程序的要求是只要两个User的id一样，那么则认为这两个
    //User是同一个User,这时我们重写equals方法，但是不重写hashCode方法，这样就会导致一个问题
    //现在需要将一批User信息放到一个HashMap或者Set集合里，User1和User2它们的id相同，但是
    //姓名不同，这时我们系统逻辑定义的是这两个User是同一个User，但是他们却能够放入同一个集合
    //这样做显然是不恰当的
    //另外HashMap的put方法首先根据key的hashCode()方法然后通过一定的计算得到一个hash值
    //然后用这个hash值对哈希表的容量取模得到key在元素中的位置，如果该位置上还没有元素，
    //则直接插入，如果已经有元素了，那么就要调用key的equals()方法和已经存在的key比较
    //如果返回true，则认为新建的key已经存在，用新值去替换旧值，然后put方法返回旧值
    //如果equals方法返回false， 则认为新健和已经存在的健不一样，这时产生健冲突，就会
    //新建一个Node插入到当前数组位置的链表的尾部

    public static void main(String[] args) {
//        String s1 = "AAAA";
//        String s2 = "AAAA";
//        String s3 = new String("AAAA");
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s1.hashCode());
//        System.out.println(s2.hashCode());
//        System.out.println(s3.hashCode());
//        Map<String, String> hashMap = new HashMap<>();
//        hashMap.put(s1, "1111");
//        hashMap.put(s2, "2222");
//        hashMap.put(s3, "3333");
//        System.out.println(hashMap);
        User user1 = new User();
        user1.setId(1);
        user1.setName("AAAA");
        User user2 = new User();
        user2.setId(1);
        user2.setName("AAAA");
        System.out.println(user1.equals(user2));
//        System.out.println(user1.hashCode());
//        System.out.println(user2.hashCode());
        //@Data注解会默认重写hashCode()方法，重写的hashCode()方法是根据对象每一个属性的值来进行计算的
        //即只要两个对象的各个属性的值都相等，那么这两个对象调用hashCode()方法将得到相同的结果
        Map<User, String> userMap = new HashMap<>();
        userMap.put(user1, user1.getName());
        userMap.put(user2, user2.getName());
        System.out.println(userMap.size());
    }

}
