package com.shyfay.usual;

/**
 * 本例用于欺骗虚拟机
 * 两个fun1方法是无法通过编译的，因为Java的泛型是伪泛型，即类型擦除。在编译期List<Integer>
 * 和List<String>编译器认为这两个类型是同一种类型，
 * 两个fun2方法也是无法通过编译的，因为在Java里，方法的返回值并不参与方法的重载
 * 但是在jdk6中两个fun3是可以同时存在的
 * @author mx
 * @since 2019/6/15
 */
public class DeceptionJVM {
//    public void fun1(List<Integer> integerList){
//        System.out.println("aaaa");
//    }
//
//    public void fun1(List<String> stringList){
//        System.out.println("bbbb");
//    }

//    public String fun2(List<Integer> integerList){
//        return "1111";
//    }
//
//    public Integer fun2(List<Integer> integerList){
//        return 1111;
//    }
//    public Integer fun3(List<Integer> integerList){
//        return 1;
//    }
//
//    public String fun3(List<String> stringList){
//        return "1111";
//    }
}
