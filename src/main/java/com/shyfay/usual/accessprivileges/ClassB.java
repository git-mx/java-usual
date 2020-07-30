package com.shyfay.usual.accessprivileges;

/**
 * java的权限关键字
 * 	- public所修饰的变量或者方法或者类 能够被其他所有的类直接访问
 * 	- 默认 就是不写任何权限关键字，它所修饰的类成员只能被同一个包下的类访问
 * 	- private 被修饰的成员只能在定义成员的类里被访问，但是一般提供getter和setter方法供外部访问
 * 	- protected 父类的被protected修饰的类成员包内可见，并且对其子类可见
 * 	            父类和子类不在同一个包内，子类只能访问从父类继承过来的protected成员，不能访问父类实例化的成员。
 *
 * @author mx
 * @since 2019/6/27
 */
public class ClassB {
    public static void main(String[] args) {
        ClassA classA = new ClassA();
        System.out.println(classA.publicStr);
        System.out.println(classA.getPrivateStr());
        System.out.println(ClassA.staticStr);
        System.out.println(classA.defaultStr);
        System.out.println(classA.protectedStr);
    }
}
