package com.shyfay.usual.delegate.cdlib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Notes cglib的方法增强器 MethodInterceptor 是在spring-aop包下的
 * @Author muxue
 * @Since 8/29/2020
 */
public class MyMethodInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("我是房东的代理，我正在约谈购房者...");
        System.out.println("我是房东的代理，我正在准备合同...");
        Object intercept = methodProxy.invokeSuper(o, args);
        System.out.println("我是房东的代理，我正在打扫房屋的卫生...");
        return intercept;
    }
}
