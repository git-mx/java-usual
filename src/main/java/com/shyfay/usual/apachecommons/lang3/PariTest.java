package com.shyfay.usual.apachecommons.lang3;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.AbstractMap;

/**
 * @Notes commons-lang3的Pair接口 用于返回多个值（配对）
 * 其实这种数据结构非常有用，比如使用一个方法返回多个值的情况，即不用使用Map也不用定义一个新的类
 * 实际工作中可以这样用： ImmutablePair<User, School>
 * @Author muxue
 * @Since 8/5/2020
 */
public class PariTest {
    public static void main(String[] args) {
        //返回配对值可以用以下几种方式
        //1.java自带的Pair
        javafx.util.Pair<Integer, String> par = new javafx.util.Pair<>(1, "one");
        System.out.println(par.getKey() + ":" + par.getValue());

        //2.JDK自带的内部类  AbstractMap.SimpleEntry和AbstractMap.SimpleImmutableEntry
        AbstractMap.SimpleEntry<Integer, String> entry = new AbstractMap.SimpleEntry<>(1, "one");
        System.out.println(entry.getKey() + ":" + entry.getValue());

        //3.commons-lang3提供的Pair的实现类：ImmutablePair 和 MutablePair

        Pair<Integer, String> pair = Pair.of(1, "one");
        Integer integer = pair.getLeft();
        String string = pair.getRight();
        System.out.println(integer + string);

        //不可变
        pair = ImmutablePair.of(2, "two");

        //可变
        MutablePair pair1 = MutablePair.of(3, "three");
        pair1.setLeft(4);
        pair1.setRight("four");

        //Triple MutableTriple ImmutableTriple 返回三个元素的配对值

    }
}
