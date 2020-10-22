package com.shyfay.usual.delegate.jdk;
import com.shyfay.usual.delegate.Delegate;
import com.shyfay.usual.delegate.Owner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/**
 * JDK动态代理，从名字上就直到，是JDK在运行时帮开发者动态生成了一个类来实现开发者想要的功能
 * 在JVM启动时加上这个参数：-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 * 即可查看JVM为我们动态生成的类
 * @Return
 * @Author muxue
 * @Since 8/29/2020
 */
public class DynamicDelegate {
    public static void main(String[] args){
        try{
            Delegate delegate = new Owner();
            InvocationHandler handler = new MyInvocationHandler(delegate);
            //getClassLoader获得类加载器
            //getInterfaces获得类实现的接口列表
            //getClasses获取当前类及其父类的所有被public修饰符修饰的所有内部类
            //getDeclaredClasses获取当前类所有的内部类
            System.out.println(delegate.getClass().getInterfaces()[0].getName());
            Delegate proxy = (Delegate) Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
                    delegate.getClass().getInterfaces(),
                    handler);
            proxy.sell();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


