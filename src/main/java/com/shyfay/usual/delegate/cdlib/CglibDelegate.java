package com.shyfay.usual.delegate.cdlib;

import com.shyfay.usual.delegate.Owner;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Notes
 * @Author muxue
 * @Since 8/29/2020
 */
public class CglibDelegate {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Owner.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Owner owner = (Owner) enhancer.create();
        owner.sell();
    }
}
