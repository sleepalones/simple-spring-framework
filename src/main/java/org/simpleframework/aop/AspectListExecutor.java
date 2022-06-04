package org.simpleframework.aop;

import cn.hutool.core.collection.CollUtil;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop.aspect.AspectInfo;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;

/**
 * @author brotherming 往被代理的类添加横切逻辑
 * @createTime 2022年05月24日 17:51:00
 */
public class AspectListExecutor implements MethodInterceptor {

    //被代理的类
    private Class<?> targetClass;

    private List<AspectInfo> sortAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList) {
        this.targetClass = targetClass;
        this.sortAspectInfoList = sortAspectInfoList(aspectInfoList);
    }

    /**
     * 按照order的值进行升序排序，确保order最小的aspect先被织入
     */
    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
        aspectInfoList.sort((Comparator.comparingInt(AspectInfo::getOrderIndex)));
        return aspectInfoList;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnValue = null;
        collectAccurateMatchedAspectList(method);
        if (CollUtil.isEmpty(sortAspectInfoList)) {
            return methodProxy.invokeSuper(proxy,args);
        }
        //1.按照order的顺序升序执行完所有Aspect的before方法
        invokeBeforeAdvices(method,args);
        try {
            //2.执行被代理类的方法
            returnValue = methodProxy.invokeSuper(proxy,args);
            //3.如果被代理方法正常返回，则按照order的顺序降序执行完所有Aspect的afterReturning方法
            returnValue = invokeAfterReturningAdvices(method,args,returnValue);
        } catch (Throwable e) {
            //4.如果被代理方法抛出异常，则按照order的顺序降序执行完所有Aspect的afterThrowing方法
            invokeAfterThrowingAdvices(method,args,e);
        }
        return returnValue;
    }

    private void collectAccurateMatchedAspectList(Method method) {
        sortAspectInfoList.removeIf(next -> !next.getPointcutLocator().accurateMatches(method));
    }

    //按照order的顺序升序执行完所有Aspect的before方法
    private void invokeBeforeAdvices(Method method, Object[] args) throws Throwable {
        for (AspectInfo aspectInfo : sortAspectInfoList) {
            aspectInfo.getAspectObject().before(targetClass,method,args);
        }
    }

    //如果被代理方法正常返回，则按照order的顺序降序执行完所有Aspect的afterReturning方法
    private Object invokeAfterReturningAdvices(Method method, Object[] args, Object returnValue) throws Throwable {
        Object result = null;
        for (int i = sortAspectInfoList.size() - 1; i >= 0; i--) {
            result = sortAspectInfoList.get(i).getAspectObject().afterReturning(targetClass,method,args,returnValue);
        }
        return result;
    }

    //如果被代理方法抛出异常，则按照order的顺序降序执行完所有Aspect的afterThrowing方法
    private void invokeAfterThrowingAdvices(Method method, Object[] args, Throwable e) throws Throwable {
        for (int i = sortAspectInfoList.size() - 1; i >= 0; i--) {
            sortAspectInfoList.get(i).getAspectObject().afterThrowing(targetClass,method,args,e);
        }
    }

}
