package com.shyfay.usual.container.map;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @Notes IdentityHashMap 它允许放入"重复的"key
 * 它在计算hashCode的时候是使用System.identityHashCode()来进行计算的
 * 并且它在put方法时进行key的判断是使用的 == 而不是 equals
 * 由此可见IdentityHashMap的key可以看作是内存起始地址，只要内存起始地址不一样的对象都可以共存
 * 所以只要不是常量，即使值相等的两个对象也是可以放入同一个IdentityHashMap的
 *
 * @Author muxue
 * @Since 8/2/2020
 */
public class IdentityHashMapTest {
    public static void main(String[] args) {
        Map<String, String> stringMap = new IdentityHashMap<>();
        stringMap.put("a", "aaaa");
        stringMap.put("a", "bbbb");
        stringMap.put("a", "cccc");
        System.out.println(stringMap.size());
        System.out.println(stringMap.get("a"));
        stringMap = new IdentityHashMap<>();
        stringMap.put(new String("a"), "aaaa");
        stringMap.put(new String("a"), "bbbb");
        stringMap.put(new String("a"), "cccc");
        System.out.println(stringMap.size());
        System.out.println(stringMap.get(new String("a")));
        stringMap = new IdentityHashMap<>();
        String s1 = "a";
        String s2 = "a";
        String s3 = "a";
        stringMap.put(s1, "aaaa");
        stringMap.put(s2, "bbbb");
        stringMap.put(s3, "cccc");
        System.out.println(stringMap.size());
        System.out.println(stringMap.get(s1));

        stringMap = new IdentityHashMap<>();
        String s4 = new String("a");
        String s5 = new String("a");
        String s6 = new String("a");
        stringMap.put(s4, "aaaa");
        stringMap.put(s5, "bbbb");
        stringMap.put(s6, "cccc");
        System.out.println(stringMap.size());
        System.out.println(stringMap.get(s4));

    }
}
