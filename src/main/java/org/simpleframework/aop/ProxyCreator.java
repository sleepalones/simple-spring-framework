package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author brotherming
 * @createTime 2022年05月24日 21:56:00
 */
public class ProxyCreator {

    /**
     * 创建动态代理对象并返回
     * @param targetClass 被代理的Class对象
     * @param methodInterceptor 方法拦截器
     * @return 返回值
     */
    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor) {
        return Enhancer.create(targetClass,methodInterceptor);
    }
    
}
