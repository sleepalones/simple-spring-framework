package org.simpleframework.mvc.render.impl;

import cn.hutool.json.JSONUtil;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.render.ResultRender;

import java.io.PrintWriter;

/**
 * @author brotherming json渲染器
 * @createTime 2022年06月07日 10:16:00
 */
public class JsonResultRender implements ResultRender {

    private Object jsonData;

    public JsonResultRender(Object result) {
        this.jsonData = result;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        //设置响应头
        requestProcessorChain.getResponse().setContentType("application/json");
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");
        //响应流写入经过格式化后的处理结果
        try (PrintWriter writer = requestProcessorChain.getResponse().getWriter()){
            writer.write(JSONUtil.toJsonStr(jsonData));
            writer.flush();
        }
    }
}
