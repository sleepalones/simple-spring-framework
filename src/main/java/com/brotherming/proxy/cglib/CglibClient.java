package com.brotherming.proxy.cglib;

import com.brotherming.proxy.cglib.impl.TargetInterfaceImpl;

/**
 * @author brotherming
 * @createTime 2022年06月01日 10:25:00
 */
public class CglibClient {
    public static void main(String[] args) {
        TargetProxy proxyClass = new TargetProxy();
        TargetInterface targetClass = proxyClass.getProxy(TargetInterfaceImpl.class);
        targetClass.sayHello("fan");
    }
}
