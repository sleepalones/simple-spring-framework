package org.simpleframework.util;

import org.junit.Test;

import java.util.Set;

/**
 * @author brotherming
 * @createTime 2022年05月10日 16:11:00
 */
public class ClassUtilTests {

    @Test
    public void extractPackageClass() {
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.brotherming.entity");
        System.out.println(classSet);
    }

}
