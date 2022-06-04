package org.simpleframework.aop;

import com.brotherming.controller.superadmin.HeadLineOperationController;
import org.junit.Test;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;

/**
 * @author brotherming
 * @createTime 2022年05月30日 22:28:00
 */

public class AspectWeaverTest {

    @Test
    public void doAopTest() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBean("com.brotherming");
        AspectWeaver aspectWeaver = new AspectWeaver();
        aspectWeaver.doAop();
        DependencyInjector dependencyInjector = new DependencyInjector();
        dependencyInjector.doIoc();
        HeadLineOperationController headLineOperationController = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        headLineOperationController.addHeadLine(null,null);
    }
}
