package com.brotherming.service.combine.impl;

import com.brotherming.entity.dto.MainPageInfoDTO;
import com.brotherming.entity.dto.Result;
import com.brotherming.service.combine.HeadLineShopCategoryCombineService;
import com.brotherming.service.solo.HeadLineService;
import com.brotherming.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:34:00
 */
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    @Autowired
    private HeadLineService headLineService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        // 获取头条列表
        // 获取店铺类别列表
        // 合并两者并返回
        return null;
    }
}
