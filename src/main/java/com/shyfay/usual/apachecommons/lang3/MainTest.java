package com.shyfay.usual.apachecommons.lang3;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Notes
 * @Author muxue
 * @Since 11/26/2020
 */
public class MainTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        System.out.println(StringUtils.join(list, ","));
    }
}
