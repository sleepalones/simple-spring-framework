package org.simpleframework.core;

import org.junit.Test;

/**
 * @author brotherming
 * @createTime 2022年05月10日 17:52:00
 */
public class BeanContainerTests {

    private static BeanContainer beanContainer = BeanContainer.getInstance();

    @Test
    public void loadBeansTest() {
        System.out.println(beanContainer.isLoaded());
        beanContainer.loadBean("com.brotherming");
        System.out.println(beanContainer.isLoaded());
        System.out.println(beanContainer.size());
    }
}
