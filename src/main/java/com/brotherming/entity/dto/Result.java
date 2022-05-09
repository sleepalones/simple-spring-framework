package com.brotherming.entity.dto;

import lombok.Data;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:25:00
 */
@Data
public class Result<T> {

    //请求结果的状态码
    private int code;

    //请求结果的详情
    private String msg;

    //请求返回的结果集
    private T data;

}
