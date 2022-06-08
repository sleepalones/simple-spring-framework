package org.simpleframework.jnject;

import com.brotherming.controller.frontend.MainPageController;
import org.junit.Test;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;

/**
 * @author brotherming
 * @createTime 2022年05月11日 22:05:00
 */
public class DependencyInjectorTests {

    @Test
    public void test() {
        //初始化容器
        BeanContainer beanContainer = BeanContainer.getInstance();
        //扫描注解标记的bean，并放入容器
        beanContainer.loadBeans("com.brotherming");
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        new DependencyInjector().doIoc();
    }

}
