package org.simpleframework.mvc;

import org.simpleframework.aop.AspectWeaver;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.processor.impl.ControllerRequestProcessor;
import org.simpleframework.mvc.processor.impl.JspRequestProcessor;
import org.simpleframework.mvc.processor.impl.PreRequestProcessor;
import org.simpleframework.mvc.processor.impl.StaticResourceRequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:58:00
 */
@WebServlet("/*")
public class DisPatcherServlet extends HttpServlet {

    private List<RequestProcessor> PROCESSOR = new ArrayList<>();

    @Override
    public void init() {
        //1. 初始化容器
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.brotherming");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        //2. 初始化请求处理器责任链
        PROCESSOR.add(new PreRequestProcessor());
        PROCESSOR.add(new StaticResourceRequestProcessor(getServletContext()));
        PROCESSOR.add(new JspRequestProcessor(getServletContext()));
        PROCESSOR.add(new ControllerRequestProcessor());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建责任链对象实例
        RequestProcessorChain chain = new RequestProcessorChain(PROCESSOR.iterator(),req,resp);
        //2.通过责任链模式来依次调用请求处理器对请求进行处理
        chain.doRequestProcessorChain();
        //3.对处理结果进行渲染
        chain.doRender();
    }
}
