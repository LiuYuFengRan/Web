package com.lyf.web.servlet;

import com.lyf.pojo.Brand;
import com.lyf.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    private BrandService service=new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       request.setCharacterEncoding("utf-8");
        //1.接收表单提交的数据，封装为一个Brand对象
        String brandName=request.getParameter("brandName");
        String companyName=request.getParameter("companyName");
        String ordered=request.getParameter("ordered");
        String description=request.getParameter("description");
        String status=request.getParameter("status");

        //封装为Brand对象
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));

        //2.调用service完成添加
        service.add(brand);

        //3.转发到查询所有的servlet
        request.getRequestDispatcher("selectAllservlet").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
