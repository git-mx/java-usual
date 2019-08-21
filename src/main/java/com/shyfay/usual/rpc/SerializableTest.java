package com.shyfay.usual.rpc;


import com.shyfay.usual.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 本实例用于将JAVA对象序列化，并存储到文件里，然后将文件内容反序列化成对象
 * @author mx
 * @since 2019/8/21
 */
public class SerializableTest {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user;
        for(int i=0; i<5; i++){
            user = new User();
            user.setId(i);
            user.setName("aaaa" + i);
            user.setAge("bbbb" + i);
            user.setSex("cccc" + i);
            user.setJob("dddd" + i);
            user.setAddress("eeee" + i);
            userList.add(user);
        }
        //将对象序列化到文件
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("D:/files/Serializable.txt"));
            os.writeObject(userList);
            os.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        //将文件内容反序列化成对象
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("D:/files/Serializable.txt"));
            ArrayList<User> list = new ArrayList<User>();
            list = (ArrayList<User>) is.readObject();
            for(User item : list){
                System.out.println(item.getId());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
