package com.brotherming.controller;

import com.brotherming.controller.frontend.MainPageController;
import com.brotherming.controller.superadmin.HeadLineOperationController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author brotherming
 * @createTime 2022年05月09日 16:58:00
 */
@WebServlet("/")
public class DisPatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/frontend/getMainPageInfo".equals(req.getServletPath()) && "GET".equals(req.getMethod())) {
            new MainPageController().getMainPageInfo(req,resp);
        }else if ("/superadmin/addHeadLine".equals(req.getServletPath()) && "POST".equals(req.getMethod())) {
            new HeadLineOperationController().addHeadLine(req,resp);
        }
    }
}
