package com.brotherming.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author brotherming
 * @createTime 2022年06月01日 10:19:00
 */
public class TargetProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> clazz) {
        //字节码增强的一个类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(clazz);
        //enhancer.setInterfaces(new Class[]{clazz});
        //设置回调类
        enhancer.setCallback(this);
        //创建代理类
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + "数据缓存start");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println(result);
        System.out.println(method.getName() + "数据缓存end");
        return result;
    }
}
