package com.shyfay.usual.container;

import com.shyfay.usual.User;

/**
 * @Notes
 * @Author muxue
 * @Since 4/7/2020
 */
public class NullTest {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        String name = user.getName();
        System.out.println(name);
    }

}
