package com.lyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lyf.pojo.Brand;
import com.lyf.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/jsonaddServlet")
public class jsonaddServlet extends HttpServlet {
    private BrandService service=new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        //1.接收数据，request.getParameter无法接受数据
        //获取请求体数据
        BufferedReader br=request.getReader();
        String params=br.readLine();

        //将JSON字符串转化为Java对象
        Brand brand= JSON.parseObject(params,Brand.class);
       /* System.out.println(brand);*/

        //2.调用service添加
        service.add(brand);

        //3.响应成功标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
