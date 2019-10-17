package com.shyfay.usual;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mx
 * @since 2019/7/18
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String sex;
    private String age;
    private String address;
    private String job;
    private String test;
}
