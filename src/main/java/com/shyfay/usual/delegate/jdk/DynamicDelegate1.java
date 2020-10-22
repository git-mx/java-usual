package com.shyfay.usual.delegate.jdk;

import com.shyfay.usual.delegate.Delegate;
import com.shyfay.usual.delegate.Owner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Notes
 * @Author muxue
 * @Since 8/29/2020
 */
public class DynamicDelegate1 {
    public static void main(String[] args) throws Exception {
        Class<?> proxyClass = Proxy.getProxyClass(DynamicDelegate1.class.getClassLoader(), Delegate.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        InvocationHandler invocationHandler = new MyInvocationHandler(new Owner());
        Delegate delegate = (Delegate) constructor.newInstance(invocationHandler);
        delegate.sell();
    }
}
