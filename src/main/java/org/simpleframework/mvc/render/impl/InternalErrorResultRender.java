package org.simpleframework.mvc.render.impl;

import cn.hutool.http.HttpStatus;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.render.ResultRender;

/**
 * @author brotherming 内部异常渲染器
 * @createTime 2022年06月07日 10:18:00
 */
public class InternalErrorResultRender implements ResultRender {

    private String errorMsg;

    public InternalErrorResultRender(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpStatus.HTTP_INTERNAL_ERROR,errorMsg);
    }
}
