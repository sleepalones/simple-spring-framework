package com.brotherming.proxy.cglib.impl;

import com.brotherming.proxy.cglib.TargetInterface;

/**
 * @author brotherming
 * @createTime 2022年06月01日 10:17:00
 */
public class TargetInterfaceImpl implements TargetInterface {
    @Override
    public String sayHello(String name) {
        //限流
        return "hello, " + name;
    }

    @Override
    public String sayThanks(String name) {
        //限流
        return "Thanks, " + name;
    }
}
