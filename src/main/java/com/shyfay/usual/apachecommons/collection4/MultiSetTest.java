package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

import java.util.Arrays;

/**
 * @Notes 允许值重复的Set,并且可以实现快速统计
 * @Author muxue
 * @Since 8/15/2020
 */
public class MultiSetTest {
    public static void main(String[] args) {
        MultiSet<String> multiSet = new HashMultiSet<>();
        multiSet.add("aaa");
        multiSet.add("aaa");
        multiSet.add("bbb", 3);
        System.out.println(multiSet.size());
        multiSet.remove("aaa");
        System.out.println(multiSet.getCount("aaa"));
        multiSet.remove("bbb", multiSet.getCount("bbb"));
        multiSet.removeAll(Arrays.asList("aaa","bbb"));
    }
}
