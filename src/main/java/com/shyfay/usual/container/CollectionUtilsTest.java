package com.shyfay.usual.container;

import com.shyfay.usual.User;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mx
 * @since 2019/7/18
 */
public class CollectionUtilsTest {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        for(int i=1; i<11; i++){
            User user = new User();
            user.setId(i);
            user.setName("name" + i);
            user.setSex("sex" + i);
            user.setAge(i);
            user.setAddress("address" + i);
            user.setJob("job" + i);
            userList.add(user);
        }
        TestClass testClass = new TestClass();
        testClass.setUserList((List<User>)CollectionUtils.select(userList, item -> {
            if(item.getAddress().contains("1")){
                testClass.setName(item.getName());
                return false;
            }
            return true;
        }));
        System.out.println(testClass);
    }
}
