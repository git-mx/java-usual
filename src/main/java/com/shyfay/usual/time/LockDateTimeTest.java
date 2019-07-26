package com.shyfay.usual.time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mx
 * @since 2019/7/17
 */
public class LockDateTimeTest {

    private static Map<String, String> weekDays = new HashMap<>();
    static{
        weekDays.put("0", "周一");
        weekDays.put("1", "周二");
        weekDays.put("2", "周三");
        weekDays.put("3", "周四");
        weekDays.put("4", "周五");
        weekDays.put("5", "周六");
        weekDays.put("6", "周日");
    }

    /**
     * 从传入时间开始取一个周
     * @param timeStamp
     * @return
     */
    private static List<String> weekDayTransfer(Long timeStamp){
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timeStamp/1000,0, ZoneOffset.of("+8"));
        Integer weekDay = localDateTime.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek()) - 1;
        List<String> resultList = new ArrayList<>();
        for(int i=0; i<7; i++){
            resultList.add(weekDays.get(String.valueOf((weekDay+i)%7)));
        }
        return resultList;
    }

    public static void main(String[] args) {
        //获取指定
        System.out.println(weekDayTransfer(1563181385000L));
    }
}
