package org.simpleframework.mvc.type;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brotherming
 * @createTime 2022年06月07日 22:58:00
 * 存储处理完后的结果数据，以及显示该数据的视图
 */
public class ModelAndView {

    //页面所在的路径
    @Getter
    private String view;

    //页面的data数据
    @Getter
    private Map<String,Object> model = new HashMap<>();

    public ModelAndView setView(String view) {
        this.view = view;
        return this;
    }

    public ModelAndView addViewData(String attributeName, Object attributeValue) {
        model.put(attributeName,attributeValue);
        return this;
    }
}
