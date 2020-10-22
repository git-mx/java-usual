package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections4.SetUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Notes SetUtils
 * @Author muxue
 * @Since 8/15/2020
 */
public class SetUtilsTest {
    public static void main(String[] args) {

        Set<String> set1 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        Set<String> set2 = new HashSet<String>(){{
            add("c");
            add("d");
            add("e");
        }};

        //取出第一个元素中，在第一个元素中不存在的元素组成一个新的SetView返回
        SetUtils.SetView<String> difference = SetUtils.difference(set1, set2);
        System.out.println(difference); //[a,b]

        Set<String> strings = difference.toSet();
        System.out.println(strings); //[a,b]

        //取出两个set之间有差异的元素组成一个新的SetView返回
        SetUtils.SetView<String> difference1 = SetUtils.disjunction(set1, set2);
        System.out.println(difference); //[a, b, d, e]

        strings = difference.toSet();
        System.out.println(strings); //[a, b, d, e]

        //union 合并两个Set
        SetUtils.SetView<String> union = SetUtils.union(set1, set2);
        strings = union.toSet();
        System.out.println(strings);
    }
}
