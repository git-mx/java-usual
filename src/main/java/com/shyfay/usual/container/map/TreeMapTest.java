package com.shyfay.usual.container.map;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @Notes TreeMap是基于红黑树实现的，它继承了SrotedMap和NavigabelMap
 * 而NavigableMap又继承自SortedMap
 * SortedMap:
 * 1.subMap(K fromKey, K toKey); 返回子SortedMap
 * 2.hadMap(K toKey);返回子SortedMap
 * 3.tailMap(K fromKey);
 * 4.fristKey();
 * 5.lastKey();
 * NavigableMap 继承自SortedMap，它又自定义了一些导航方法
 * 1.lowerEntry(K key); //返回第一个小于key的Entry floorEntry 小于等于 higherEntry/ceilingEntry
 * 2.lowerKey(K key);//返回第一个小于key的K floorKey 小于等于 higherKey/ceilingKey
 * 3.firstEntry/firstKey lastEntry/lastKey
 * 4.pollFirstEntry();删除并返回最小的key pollLastEntry();最大的
 * 5.navigableKeySet(); 返回key升序排列的NavigableKeySet
 * 6.descendingKeySet(); 返回key降序排列的NavigableKeySet
 *
 * @Author muxue
 * @Since 8/15/2020
 */
public class TreeMapTest {
    public static void main(String[] args) {
        NavigableMap<String, Integer> map = new TreeMap<>();
        map.put("aa", 11);
        map.put("bb", 22);
        map.put("cc", 33);
        map.put("dd", 44);
        map.put("ee", 55);
        map.put("ff", 55);
        map.put("gg", 55);
        System.out.println(map.size());
        System.out.println(map.ceilingKey("cc"));
        System.out.println(map.floorKey("cc"));
        System.out.println(map.higherKey("cc"));
        System.out.println(map.lowerKey("cc"));

        System.out.println(map.ceilingEntry("cc"));
        System.out.println(map.floorEntry("cc"));
        System.out.println(map.higherEntry("cc"));
        System.out.println(map.lowerEntry("cc"));

        System.out.println(map.navigableKeySet());
        System.out.println(map.descendingKeySet());



        System.out.println(map.headMap("cc"));
        System.out.println(map.headMap("cc", true));
        System.out.println(map.tailMap("cc"));
        System.out.println(map.tailMap("cc", true));

        System.out.println(map.subMap("cc", "ee"));
        System.out.println(map.subMap("cc", true, "ee", true));

        System.out.println(map.pollFirstEntry());
        System.out.println(map.pollLastEntry());

        System.out.println(map.descendingMap());
    }
}
