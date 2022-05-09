package com.brotherming.service.combine;

import com.brotherming.entity.dto.MainPageInfoDTO;
import com.brotherming.entity.dto.Result;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:32:00
 */
public interface HeadLineShopCategoryCombineService {

    Result<MainPageInfoDTO> getMainPageInfo();

}
