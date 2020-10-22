package com.shyfay.usual.apachecommons.collection4;

import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Notes ListUtilsTest
 * @Author muxue
 * @Since 8/15/2020
 */
public class ListUtilsTest {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        List<String> list2 = new ArrayList<String>(){{
            add("c");
            add("d");
            add("e");
        }};
        //subtract 相当于做减法
        List<String> newList = ListUtils.subtract(list1, list2);
        System.out.println("newList:" + newList);
        //取出交集 并且返回一个新的List
        List<String> intersection = ListUtils.intersection(list1, list2);

        System.out.println(intersection); //[c]

        //这个方法也能取出交集的效果 但是会直接改变list1里面的元素  list2不变
        list1.retainAll(list2);
        System.out.println(list1); // [c]
        System.out.println(list2); //[c, d, e]

        //partition 切割



        //sum 取并集，去重
        newList = ListUtils.sum(list1, list2);

        //union 直接取并集，但是不去重

    }
}
