package com.shyfay.usual.regular;

/**
 * @Notes 常用的正则表达式
 * @Author muxue
 * @Since 8/5/2020
 */
public class UsualRegular {
    public static void main(String[] args) {
        //1.去掉字符串末尾所有的0
        String str = "123.08900";
        str = str.replaceAll("(0)+$", "");
        System.out.println(str);
    }
}
