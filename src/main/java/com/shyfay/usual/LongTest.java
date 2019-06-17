package com.shyfay.usual;

/**
 * @author 牟雪
 * @since 2019/3/12
 */
public class LongTest {
    private static final Long finalLong = 0L;
    public static void main(String[] args){
        if(finalLong.longValue() == 0){
            System.out.println("AAAA");
        }else{
            System.out.println("BBBB");
        }
    }
}
