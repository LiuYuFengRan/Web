package com.lyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lyf.pojo.Brand;
import com.lyf.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/jsonSelectAllServlet")
public class jsonSelectAllServlet extends HttpServlet {
    private BrandService service=new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //1.调用BrandService完成查询

        List<Brand> brands= service.selectAll();
        //2.将集合对象转化为JSON数据  序列化
        String jsonString = JSON.toJSONString(brands);
        //3.响应数据
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
