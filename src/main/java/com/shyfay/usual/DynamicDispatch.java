package com.shyfay.usual;
public class DynamicDispatch {
    public static class Human{
        public void say(){
            System.out.println("human...");
        }
    }
    public static class Man extends Human {
        @Override
        public void say(){
            System.out.println("man...");
        }
    }
    public static class Woman extends Human{
        @Override
        public void say(){
            System.out.println("woman...");
        }
    }
    public static void main(String[] args){
        Human human = new Human();
        Human man = new Man();
        Human woman = new Woman();
        human.say();
        man.say();
        woman.say();
    }
    //TODO 方法参数的静态类型是在编译时确定的，而方法的调用者的身份却不是方法的静态类型，而是调用者的实际类型
    //TODO 而对象的实际类型只有在程序运行时才能确定，即在运行时确定方法的调用者身份这一动作称为动态分派
    //TODO 上面的三个say方法分别执行时输出结果为human... man... woman...
    //TODO 这里三个man.say()时，man是say方法的调用者，它的静态类型是Human，而它的实际类型则是Man，所以根据
    //TODO 动态分派的规则，会调用实际类型的say方法，所以这里输出man...
    //TODO 这就是JAVA面向对象三大特性"重载"的精髓
}
