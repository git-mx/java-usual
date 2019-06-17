package com.shyfay.usual;

/**
 * @author mx
 * @since 2019/4/25
 */
public class ShiftTest {
    public static void main(String[] args){
        int shift = 0;

        for(int value = 1; value < 16 && value < 65535; ++shift) {
            value <<= 1;
        }

        System.out.println(shift);
        System.out.println(1 << 4);
    }


}
