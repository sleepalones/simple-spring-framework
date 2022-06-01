package com.brotherming.proxy.jdk;

import com.brotherming.proxy.jdk.impl.TargetInterfaceImpl;

/**
 * @author brotherming
 * @createTime 2022年05月31日 23:05:00
 */
public class JDKClient {

    public static void main(String[] args) {

        //获取代理类（还不是真正的代理对象）
        TargetProxy proxyClass = new TargetProxy(new TargetInterfaceImpl());

        //真正的代理对象
        TargetInterface targetClass = proxyClass.getProxy();

        targetClass.sayHi();
        targetClass.work();

    }

}
