package com.brotherming.service.solo;

import com.brotherming.entity.HeadLine;
import com.brotherming.entity.dto.Result;

import java.util.List;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:27:00
 */
public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);

    Result<Boolean> removeHeadLine(int headLineId);

    Result<Boolean> modifyHeadLine(HeadLine headLine);

    Result<HeadLine> queryHeadLineById(int headLineId);

    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);

}
