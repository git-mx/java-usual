package com.shyfay.usual.java8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Notes java8采用了全新的日期和时间API
 * Instant 精确到纳秒
 * LocalDate
 * LocalTime
 * LocalDateTime
 * ZonedDateTime
 * Clock
 * Period
 * Duration
 * Timezones
 * 新的API里LocalDate、LocalTime、LocalDateTime里已经包含了parse和format等方法。
 * 不需要借助额外的DateFormater来进行时间和字符串的互转
 * @Author muxue
 * @Since 7/28/2020
 */
public class TimeTest {
    public static void main(String[] args) {
        //最准确的获取当地当前时间
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now(shanghai);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
