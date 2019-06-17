package com.shyfay.usual;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author 牟雪
 * @since 2018/11/13
 */
public class ClassInit {
    public static void main(String[] args){
        Long stamp = 1543590000000L;
        String pattern = "M月d日H点";
        LocalDateTime emYearMonthTemp = LocalDateTime.ofEpochSecond(stamp / 1000, 0, ZoneOffset.of("+8"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        System.out.println(emYearMonthTemp.format(formatter));
    }
}
