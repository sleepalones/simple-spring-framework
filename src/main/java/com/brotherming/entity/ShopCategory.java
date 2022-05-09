package com.brotherming.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:11:00
 */
@Data
public class ShopCategory {

    //主键ID
    private Long shopCategoryId;

    //类别名
    private String shopCategoryName;

    //类别描述
    private String ShopCategoryDesc;

    //类别图片地址
    private String shopCategoryImg;

    //权重
    private Integer priority;

    //创建时间
    private Date createTime;

    //最近更新时间
    private Date lastEditTime;

    //父类别
    private ShopCategory parent;

}
