package com.brotherming.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author brotherming
 * @createTime 2022年05月31日 23:05:00
 */
public class TargetProxy implements InvocationHandler {

    private Object target;

    public TargetProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取真正的代理类
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    /**
     * 该方法会对目标接口的方法进行拦截
     * @param proxy 这个就是我们那个代理类，就是jdk生成的那个叫 $Proxy 代理类
     * @param method 就是目标接口的方法，比如 sayHi() work() 的反射对象 Method
     * @param args 就是目标接口的方法，比如 sayHi() work() 的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置增强（通知）........");
        //调用目标接口的方法
        Object invoke = method.invoke(target, args);
        if ("sayHi".equals(method.getName())) {
            System.out.println("执行sayHi方法");
        }
        System.out.println("后置增强（通知）........");
        return invoke;
    }
}
