package com.shyfay.usual.container;

import com.shyfay.usual.User;
import lombok.Data;

import java.util.List;

/**
 * @author mx
 * @since 2019/7/18
 */
@Data
public class TestClass {
    private String name;
    List<User> userList;
}
