package org.simpleframework.core;

import com.brotherming.controller.frontend.MainPageController;
import com.brotherming.service.solo.HeadLineService;
import com.brotherming.service.solo.impl.HeadLineServiceImpl;
import org.junit.Test;
import org.simpleframework.core.annotation.Controller;

import java.util.Set;

/**
 * @author brotherming
 * @createTime 2022年05月10日 17:52:00
 */
public class BeanContainerTests {

    private static BeanContainer beanContainer = BeanContainer.getInstance();

    @Test
    public void test() {
        //扫描该包下被自定义注解标记的所有类
        beanContainer.loadBeans("com.brotherming");
        Set<Class<?>> classes = beanContainer.getClasses();
        Set<Object> beans = beanContainer.getBeans();
        Set<Class<?>> classesByAnnotation = beanContainer.getClassesByAnnotation(Controller.class);
        Object bean = beanContainer.getBean(MainPageController.class);
        System.out.println(bean instanceof MainPageController);
        System.out.println(beanContainer.isLoaded());
        System.out.println(beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }
}
