package com.shyfay.usual.string;

import java.util.StringJoiner;

/**
 * @Notes
 * @Author muxue
 * @Since 8/29/2020
 */
public class StringJoinerTest {
    public static void main(String[] args) {
//        StringJoiner stringJoiner = new StringJoiner(",");
//        for(int i=100; i<=110; i++){
//            stringJoiner.add("'" + i + "' ");
//        }
//        System.out.println(stringJoiner);
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.setEmptyValue("AAAA");
        System.out.println(stringJoiner);

    }
}
