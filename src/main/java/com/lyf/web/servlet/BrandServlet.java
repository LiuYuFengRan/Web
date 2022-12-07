package com.lyf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lyf.pojo.Brand;
import com.lyf.pojo.PageBean;
import com.lyf.service.BdService;
import com.lyf.service.impl.BdServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BdService brandService = new BdServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //1.调用service查询
        List<Brand> brands= brandService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(brands);
        System.out.println("brandservlet_selectall"+jsonString);
        //3.写数据
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        //1.接收id数据 [1,2,3]
        BufferedReader br = request.getReader();
        String params=br.readLine();//json字符串
        //转为int[]
        int[] ids = JSON.parseObject(params, int[].class);
        //2.调用service添加
        brandService.deleteByIds(ids);
        //3.相应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");

        request.setCharacterEncoding("utf-8");
        //1.接收参数  当前页码和每页展示条数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int pageSize = Integer.parseInt(_pageSize);
        int currentPage = Integer.parseInt(_currentPage);

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //1.接收参数  当前页码和每页展示条数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int pageSize = Integer.parseInt(_pageSize);
        int currentPage = Integer.parseInt(_currentPage);

        //获取查询条件对象
        BufferedReader br=request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        Brand brand=JSON.parseObject(params,Brand.class);

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        //2.转为JSON
       /* brand对象转换成json会调用get方法获取brand数据*/
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据
        response.getWriter().write(jsonString);
    }
}

