package com.brotherming.controller.superadmin;

import com.brotherming.entity.HeadLine;
import com.brotherming.entity.dto.Result;
import com.brotherming.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.annotation.RequestParam;
import org.simpleframework.mvc.annotation.ResponseBody;
import org.simpleframework.mvc.type.ModelAndView;
import org.simpleframework.mvc.type.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author brotherming
 * @createTime 2022年05月09日 17:01:00
 */
@Controller
@RequestMapping(value = "/headline")
public class HeadLineOperationController {

    @Autowired(value = "HeadLineServiceImpl")
    private HeadLineService headLineService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ModelAndView addHeadLine(@RequestParam("lineName") String lineName,
                                       @RequestParam("lineLink") String lineLink,
                                       @RequestParam("lineImg") String lineImg,
                                       @RequestParam("priority") String priority) {
        HeadLine headLine = new HeadLine();
        headLine.setLineName(lineName);
        headLine.setLineLink(lineLink);
        headLine.setLineImg(lineImg);
        headLine.setPriority(priority);
        Result<Boolean> result = headLineService.addHeadLine(headLine);
        return new ModelAndView().setView("test.jsp").addViewData("result",result);
    }

    public Result<Boolean> removeHeadLine(HttpServletRequest req, HttpServletResponse resp) {
        // TODO: 参数校验以及请求参数转化
        return headLineService.removeHeadLine(1);
    }

    public Result<Boolean> modifyHeadLine(HttpServletRequest req, HttpServletResponse resp) {
        // TODO: 参数校验以及请求参数转化
        return headLineService.modifyHeadLine(new HeadLine());
    }

    public Result<HeadLine> queryHeadLineById(HttpServletRequest req, HttpServletResponse resp) {
        // TODO: 参数校验以及请求参数转化
        return headLineService.queryHeadLineById(1);
    }

    @RequestMapping(value = "/queryHeadLine",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<HeadLine>> queryHeadLine() {
        // TODO: 参数校验以及请求参数转化
        return headLineService.queryHeadLine(null,1,10);
    }

}
