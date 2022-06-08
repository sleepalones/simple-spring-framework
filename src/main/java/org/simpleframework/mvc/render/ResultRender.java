package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * @author brotherming 渲染请求结果
 * @createTime 2022年06月07日 10:13:00
 */
public interface ResultRender {

    //执行渲染
    void render(RequestProcessorChain requestProcessorChain) throws Exception;

}
