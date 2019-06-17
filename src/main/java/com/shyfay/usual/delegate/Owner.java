package com.shyfay.usual.delegate;

import com.shyfay.usual.delegate.Delegate;

//TODO 委托类-房东
public class Owner implements Delegate {
    public void sell(){
        System.out.println("我是房东，我现在在合同上签字。。。");
    }
}
