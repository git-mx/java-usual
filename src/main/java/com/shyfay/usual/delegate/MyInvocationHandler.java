package com.shyfay.usual.delegate;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//TODO 代理业务逻辑处理类
class MyInvocationHandler implements InvocationHandler {
    //TODO 代理类的对象
    private Object delegate;
    public MyInvocationHandler(Object deletgate){
        this.delegate = deletgate;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println("我是房东的代理，我正在约谈购房者...");
        System.out.println("我是房东的代理，我正在准备合同...");
        Object obj = method.invoke(delegate, args);
        System.out.println("我是房东的代理，我正在打扫房屋的卫生...");
        return obj;
    }
}
