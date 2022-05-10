package com.brotherming.service.solo.impl;

import com.brotherming.entity.HeadLine;
import com.brotherming.entity.ShopCategory;
import com.brotherming.entity.dto.Result;
import com.brotherming.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:32:00
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(HeadLine shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
