package com.brotherming.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:08:00
 */
@Data
public class HeadLine {

    //主键ID
    private Long lineId;

    //头条名字
    private String lineName;

    //头条链接，点击头条进入相应链接中
    private String lineLink;

    //头条图片
    private String lineImg;

    //权重
    private String priority;

    //0.不可用 1.可用
    private Integer enableStatus;

    //创建时间
    private Date createTime;

    //最近一次的更新时间
    private Date lastEditTime;

}
