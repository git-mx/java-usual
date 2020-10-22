package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.util.Iterator;

/**
 * @Notes 可以实现快速统计和去重
 * @Author muxue
 * @Since 8/15/2020
 */
public class BagTest {
    public static void main(String[] args) {
        Bag hashBag = new HashBag();
        String s1 = "s1";
        String s2 = "s2";
        hashBag.add(s1);
        hashBag.add(s2);
        hashBag.add(s2,3);
        Iterator<String> iterator = hashBag.iterator();
        System.out.println("包中的元素为：");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("包中的元素个数为：" + hashBag.size());
        System.out.println("包中的s2的个数为：" + hashBag.getCount(s2));
        System.out.println("去重后的个数为：" + hashBag.uniqueSet().size());
    }
}
