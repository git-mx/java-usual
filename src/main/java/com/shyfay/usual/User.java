package com.shyfay.usual;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mx
 * @since 2019/7/18
 */
@Data
public class User {
//@Getter
//@Setter
//public class User {
    //private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String job;
    private String test;

    public User(){}
    public User(Integer id){
        this.id = id;
    }
}
