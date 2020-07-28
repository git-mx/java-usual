package com.shyfay.usual.java8;

import com.shyfay.usual.User;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Notes 方法引用
 * 方法引用其实就是一个lambda表达式，方法引用提供了一种引用而不执行方法的声明。
 * 当执行这个声明时，方法引用会创建一个函数式接口的实例，利用实例来调用对应的方法
 * @Author muxue
 * @Since 7/28/2020
 */
public class FuncationReference {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("Shyfay");
        //1.对象：实例方法名
        Supplier<String> supplier = user::getName;
        System.out.println(supplier.get());
        //2.静态方法名
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(1, 2));
        //3.类：实例方法名
        BiPredicate<String, String> biPredicate = String::equals;
        System.out.println(biPredicate.test("AAAA", "AAAA"));
        //4.类::new
        //无参构造
        Supplier<User> supplier1 = User::new;
        //有参构造
        Function<Integer, User> function = User::new;
        //多个有参构造的时候不应该使用方法引用，而是直接编写new User
        //5.数组引用
        Function<Integer, String[]> function1 = String[]::new;
        String[] array = function1.apply(10);
        System.out.println(array);

        //String::valueOf等价于lambda表达式 (s) -> String.valueOf(s);
        //Math::pow 等价于 (x,y) -> Math.pow(x,y);

    }
}
