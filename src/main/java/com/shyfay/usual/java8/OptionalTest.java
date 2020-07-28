package com.shyfay.usual.java8;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @Notes Optional是JAVA8提供的新类型，它最大的作用是避免空指针，代替三目运算符
 * 1.get()获取Optional类型里面存的值
 * 2.isPresent()方法判断Optional类型是否有值
 * 3.ifPresent()方法，如果Optional类型的值不是空，则执行consumer对象
 * 4.orElse(value)：如果optional保存的的值不是null，则返回原来的则，否则返回value
 * 5.orElseGet(Supplier supplier);功能和orElse一样，只不过orElseGet的参数是一个生产者
 *   它俩的区别是，如果括号里是一个方法，前者无论是否值为空都会调用这个方法，但是后者是，如果值
 *   不为空那么则不会调用括号里面的方法。
 * 6.orElseThrow 判空场景特别有用
 * 7.map(Function function)：对于Optional中保存的值使用function进行计算，并返回一个新的Optional
 *
 * @Author muxue
 * @Since 7/27/2020
 * **/
public class OptionalTest {



    public static void main(String[] args) {
        Date date1 = null;
        Date date2 = new Date(Optional.of(date1).orElseThrow(() -> new IllegalArgumentException("时间不能为空")).getDate());
    }
}
