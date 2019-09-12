package com.shyfay.usual.polymorphic;

/**
 * @author mx
 * @since 2019/8/28
 */
public class MainTest {
    private static class Human {
        private void say(){
            System.out.println("Human...");
        }
    }

    private static class Man extends Human {
        private void say(){
            System.out.println("Man...");
        }
    }

    private static class Woman extends Human {
        private void say(){
            System.out.println("Woman...");
        }
    }

    private static void fun(Human human){
        human.say();
    }

    public static void main(String[] args) {
        Human human = new Human();
        Human man = new Man();
        Woman woman = new Woman();
        fun(woman);
        woman.say();
    }
}
