package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * @author brotherming
 * @createTime 2022年05月24日 17:22:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 需要织入横切逻辑的注解标签
     */
    Class<? extends Annotation> value();
}
