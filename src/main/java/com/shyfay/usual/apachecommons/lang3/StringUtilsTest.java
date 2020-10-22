package com.shyfay.usual.apachecommons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @Notes StringUtils
 * @Author muxue
 * @Since 8/5/2020
 */
public class StringUtilsTest {
    public static void main(String[] args) {
        //1.isEmpty isAnyEmpty isNoneEmpty isAllEmpty

        //2.isBlank isAnyBlank isNotBlank isNoneBlank isAllBlank

        //3.trim trimToNull trimToEmpty

        //4.ordinalIndexOf 一个字符串在另一个字符串里出现第ordinal次的位置 lastOrdinalIndexOf
        System.out.println(StringUtils.ordinalIndexOf("aaaabbbbccc", "c", 3));

        //5.conatinsAny

        //6.substring

        //7.jon 将一个字符串数组连接成一个字符串
        System.out.println(StringUtils.join(ArrayUtils.toArray("AAA", "BBB", "CCC")));

        //8.deleteWhitespace

        //9.removeStart

        //10.rightPad/leftPad 使用指定的字符补齐字符串到指定长度
        System.out.println(StringUtils.rightPad("abc", 5, 'z'));

        //11.center 补齐字符串到指定长度，两边用空格符号补齐
        System.out.println(StringUtils.center("ab", 5));

        //12.swapCase 将一个字符串里的所有的字符大小写调换

        //13.isAlpha 判断一个字符串里面的字符是否全是数字，这个非常有用

        //14.reverse 将字符串翻转

        //15.abbreviate 将字符串里出了前3位以外的其他字符换用...代替

        //16.warp 在字符串前后都加上指定的字符串

        //17.isAllLowerCase isAllUpperCase

    }
}
