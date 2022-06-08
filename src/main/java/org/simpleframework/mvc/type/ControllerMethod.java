package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author brotherming
 * @createTime 2022年06月07日 19:58:00
 * 待执行的Controller及其方法实例和参数的映射
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerMethod {

    //controller 对应的Class对象
    private Class<?> controllerClass;

    //执行的Controller方法实例
    private Method invokeMethod;

    //方法参数名称以及对应的参数类型
    private Map<String,Class<?>> methodParameters;

}
