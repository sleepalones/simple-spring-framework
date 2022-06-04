package com.brotherming.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Controller;

import java.lang.reflect.Method;

/**
 * @author brotherming
 * @createTime 2022年06月01日 22:44:00
 */
@Slf4j
@Aspect(value = Controller.class)
@Order(10)
public class ControllerInfoRecordAspect extends DefaultAspect {

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("方法开始执行了，执行的类是[{}]，执行的方法是[{}]，参数是[{}]",
                targetClass.getName(),method.getName(),args);
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        log.info("方法顺利完成，执行的类是[{}]，执行的方法是[{}]，参数是[{}]，返回值是[{}]",
                targetClass.getName(),method.getName(),args,returnValue);
        return returnValue;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {
        log.info("方法执行失败，执行的类是[{}]，执行的方法是[{}]，参数是[{}]，异常是[{}]",
                targetClass.getName(),method.getName(),args,e.getMessage());
    }
}