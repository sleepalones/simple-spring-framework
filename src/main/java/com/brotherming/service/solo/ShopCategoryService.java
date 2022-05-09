package com.brotherming.service.solo;

import com.brotherming.entity.HeadLine;
import com.brotherming.entity.ShopCategory;
import com.brotherming.entity.dto.Result;

import java.util.List;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:30:00
 */
public interface ShopCategoryService {

    Result<Boolean> addShopCategory(ShopCategory shopCategory);

    Result<Boolean> removeShopCategory(int shopCategoryId);

    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);

    Result<ShopCategory> queryShopCategoryById(int shopCategoryId);

    Result<List<ShopCategory>> queryShopCategory(HeadLine shopCategoryCondition, int pageIndex, int pageSize);

}
