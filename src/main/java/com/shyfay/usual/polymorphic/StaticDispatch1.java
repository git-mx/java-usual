package com.shyfay.usual.polymorphic;

import java.util.List;

/**
 * @Notes 伪泛型
 * @Author muxue
 * @Since 4/3/2020
 */
public class StaticDispatch1 {

    public void fun(List<String> ids){
        System.out.println(ids.size());
    }

//    public void fun(List<Integer> ids)){
//        System.out.println(ids.size());
//    }

}
