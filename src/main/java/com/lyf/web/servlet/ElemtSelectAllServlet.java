package com.lyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lyf.pojo.Brand;
import com.lyf.service.BdService;
import com.lyf.service.impl.BdServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ElemtSelectAllServlet")
public class ElemtSelectAllServlet extends HttpServlet {
   private BdService brandService = new BdServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        //1.调用service查询
        List<Brand> brands= brandService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(brands);
        System.out.println("selectall"+jsonString);
        //3.写数据
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
