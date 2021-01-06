package com.shyfay.usual;

import lombok.Data;

import java.io.Serializable;

/**
 * @Notes
 * @Author muxue
 * @Since 1/6/2021
 */
@Data
public class Person implements Serializable {

    private String name;

    private Integer age;

    public Person(){}

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

}
