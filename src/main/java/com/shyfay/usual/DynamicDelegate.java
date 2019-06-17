package com.shyfay.usual;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//TODO 现在假设房东要出售房子，但是房东图省事儿，他不想和购房者谈判，也不想准备合同，也不想在签完合同之后把房子打扫干净交付给购房者。
//TODO 他就只想在合同上签一下字，然后完事儿。于是房东就找了一个人带代替他做那些额外的事情。这时房东就称为委托类，
//TODO 而房东找的那个代替他做额外事情的人称为代理类。这里的委托类和代理类见Delegate类和Owner类
//TODO 客户类，去和代理购买房子
public class DynamicDelegate {
    public static void main(String[] args){
        try{
            Delegate delegate = new Owner();
            InvocationHandler handler = new MyInvocationHandler(delegate);
            //TODO getClassLoader获得类加载器
            //TODO getInterfaces获得类实现的接口列表
            //TODO getClasses获取当前类及其父类的所有被public修饰符修饰的所有内部类
            //TODO getDeclaredClasses获取当前类所有的内部类
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


