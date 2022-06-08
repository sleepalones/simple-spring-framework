package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author brotherming
 * @createTime 2022年06月07日 20:02:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPathInfo {

    //http请求方法
    private String httpMethod;

    //http请求路径
    private String httpPath;

}
