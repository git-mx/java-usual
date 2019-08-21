package com.shyfay.usual.thread.concurrent;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 本例用于演示concurrent包的ConcurrentNavigableMap
 * concrrent包下的ConcurrentNavigableMap和ConcurrentHashMap等都是线程安全的，在多线程下是可以同步访问的
 * @author mx
 * @since 2019/8/21
 */
public class ConcurrentNavigableMapTest {
    public static void main(String[] args) {
        ConcurrentNavigableMap<String, String> map = new ConcurrentSkipListMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        map.put("5", "five");
        ConcurrentNavigableMap<String, String> headMap = map.headMap("3");
        ConcurrentNavigableMap<String, String> tailMap = map.tailMap("3");
        System.out.println(headMap);
        System.out.println(tailMap);
    }
}
