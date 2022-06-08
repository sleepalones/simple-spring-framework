package org.simpleframework.mvc.render.impl;

import cn.hutool.http.HttpStatus;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.render.ResultRender;

/**
 * @author brotherming 资源找不到时使用的渲染器
 * @createTime 2022年06月07日 10:19:00
 */
public class ResourceNotFoundResultRender implements ResultRender {

    private String method;
    private String path;

    public ResourceNotFoundResultRender(String method, String path) {
        this.method = method;
        this.path = path;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpStatus.HTTP_NOT_FOUND,
                "获取不到对应的请求资源，请求路径[" + path + "]，" + "请求方法类型[" + method + "]");
    }
}
