package com.brotherming.service.solo.impl;

import com.brotherming.entity.HeadLine;
import com.brotherming.entity.dto.Result;
import com.brotherming.service.solo.HeadLineService;

import java.util.List;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:30:00
 */
public class HeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return null;
    }
}
