package com.shyfay.usual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Notes
 * @Author muxue
 * @Since 7/29/2020
 */
public class StaticList {
    public static List<User> users;
    static {
        users = new ArrayList<>();
        IntStream.iterate(1, n -> n + new Random().nextInt()).limit(20).forEach(x -> {
            User user = new User();
            user.setName("user" + x);
            user.setId(x);
            user.setAge(x);
            user.setAddress("address" + x);
            user.setJob("job" + x);
            user.setSex("sex" + x);
            user.setTest("test" + x);
            users.add(user);
        });
    }
}
