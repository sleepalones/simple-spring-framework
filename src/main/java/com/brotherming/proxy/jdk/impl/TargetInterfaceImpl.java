package com.brotherming.proxy.jdk.impl;

import com.brotherming.proxy.jdk.TargetInterface;

/**
 * @author brotherming
 * @createTime 2022年05月31日 23:04:00
 */
public class TargetInterfaceImpl implements TargetInterface {
    @Override
    public void sayHi() {
        System.out.println("sayHi");
    }

    @Override
    public void work() {
        System.out.println("work");
    }
}
