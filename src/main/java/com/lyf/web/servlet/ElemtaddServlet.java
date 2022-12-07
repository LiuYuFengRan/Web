package com.lyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lyf.pojo.Brand;
import com.lyf.service.BdService;
import com.lyf.service.impl.BdServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/ElemtaddServlet")
public class ElemtaddServlet extends HttpServlet {
   private BdService brandService = new BdServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
       //1.接收品牌数据
        BufferedReader br = request.getReader();
        String params=br.readLine();//json字符串
        //转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //2.调用service添加
        brandService.add(brand);
        //3.相应成功的标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
