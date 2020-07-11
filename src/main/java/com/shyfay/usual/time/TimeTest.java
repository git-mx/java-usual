package com.shyfay.usual.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Notes
 * @Author muxue
 * @Since 12/13/2019
 */
public class TimeTest {
    private static String datestrFormat(String source){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try{
            date = simpleDateFormat.parse(source);
        }catch (ParseException e){
            return null;
        }
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        String regular = "^[0-9]{4}([^0-9]?)";
        String source = "20110101010101";
        System.out.println(source.replaceFirst(regular, "yyyy$1"));

    }
}
