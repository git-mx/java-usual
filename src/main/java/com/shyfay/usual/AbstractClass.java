package com.shyfay.usual;
public class AbstractClass {
    public static abstract class Animal {
        private String name = "动物";
        abstract void bark();
        public void sleep(){
            System.out.println("呼呼呼~");
        }
    }
    public static class Cat extends Animal {
        Cat(){}
        private String name = "猫咪";
        public void bark() {
            System.out.println("喵喵喵~");
        }
    }
    public static class Dog extends Animal{
        private String name = "狗狗";
        Dog(){}
        public void bark(){
            System.out.println("旺旺旺");
        }
    }
    public static void main(String[] args){
        Cat cat = new Cat();
        System.out.println(cat.name);
        cat.bark();
        cat.sleep();
        Dog dog = new Dog();
        System.out.println(dog.name);
        dog.bark();
        dog.sleep();
    }
    //TODO 本实例显示了虚类的作用，父类动物都有名称属性以及叫和睡觉这俩动作。而子类喵咪和狗狗也有名称属性以及叫和睡觉这俩动作。
    //TODO 但是很明显所有的动物睡觉都是睡觉这个动作，而所有的动物它们的名称和发出的叫声是不一样的，这样所有的子类只需要覆盖
    //TODO 父类的名称属性叫这个方法，而睡觉这个方法可以直接使用父类的了。
    //TODO 虚类与接口：
    //TODO 共同点1.两者都是抽象类不能被实例化
    //TODO 2.接口的实现类和虚类的子类都必须实现它们所声明的虚方法
    //TODO 不同点1.接口需要子类实现即使用implements关键字，虚类需要子类继承即使用extends关键字
    //TODO 不同点2.一个类可以实现多个接口，但是它只能继承一个虚类
    //TODO 不同点3.接口中定义的方法必须是虚方法，而虚类中既可以定义虚方法也可以定义普通方法
    //TODO 不同点4.接口中指定只能定义常量即被static final两个关键字同时修饰的变量，而虚类中可以定义普通变量
    //TODO 接口的本质实际上就是纯虚类
}


