package com.brotherming.controller.frontend;

import com.brotherming.entity.dto.MainPageInfoDTO;
import com.brotherming.entity.dto.Result;
import com.brotherming.service.combine.HeadLineShopCategoryCombineService;
import org.simpleframework.core.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:57:00
 */
@Controller
public class MainPageController {

    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

}
