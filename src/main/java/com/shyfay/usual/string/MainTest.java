package com.shyfay.usual.string;

import java.util.Random;

/**
 * @author mx
 * @since 2019/8/30
 */
public class MainTest {

    public static void main(String[] args) {
        for(int i=0; i<23; i++){
            int a = new Random().nextInt();
            System.out.println(a);
        }
    }
}
