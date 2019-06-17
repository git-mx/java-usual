package com.shyfay.usual;
public class StaticDispatch {
    static abstract class Human{}
    static class Man extends Human{}
    static class Woman extends Human{}
    public static void say(Human human){
        System.out.println("human...");
    }
    public static void say(Man man){
        System.out.println("man...");
    }
    public static void say(Woman woman){
        System.out.println("woman...");
    }
    public static void main(String[] args){
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.say(man);
        sd.say(woman);
        //TODO 运行结果是两次输出都是human...
        //TODO man这个对象的静态类型（外观类型）是Human,而它的实际类型是Man
        //TODO woman这个对象的静态类型（外观类型）是Human,而它的实际类型是Woman
        //TODO JVM依赖参数的静态类型来确定方法的执行版本的分派动作称为的静态分派，
        //TODO 当参数没有显示的类型的时候例如say('a')这种这时候JVM只会去选择一个"更加合适"的版本。
        //TODO 例子在下一例的方法的重载优先级中进行详细阐述
        //TODO 值得注意的是静态分派在程序编译时就已经完成了
    }
}
