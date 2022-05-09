package com.brotherming.entity.dto;

import com.brotherming.entity.HeadLine;
import com.brotherming.entity.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:33:00
 */
@Data
public class MainPageInfoDTO {

    private List<HeadLine> headLineList;

    private List<ShopCategory> shopCategoryList;

}
