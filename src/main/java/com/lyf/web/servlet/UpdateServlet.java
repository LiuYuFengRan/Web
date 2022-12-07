package com.lyf.web.servlet;

import com.lyf.pojo.Brand;
import com.lyf.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private BrandService service=new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       request.setCharacterEncoding("utf-8");
        //1.接收表单提交的数据，封装为一个Brand对象
        String id=request.getParameter("id");
        System.out.println("修改id为："+id);
        String brandName=request.getParameter("brandName");
        String companyName=request.getParameter("companyName");
        String ordered=request.getParameter("ordered");
        String description=request.getParameter("description");
        String status=request.getParameter("status");

        //封装为Brand对象
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setId(Integer.parseInt(id));
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));

        //2.调用service完成添加
        service.update(brand);

        //3.转发到查询所有的servlet
        request.getRequestDispatcher("selectAllServlet").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
