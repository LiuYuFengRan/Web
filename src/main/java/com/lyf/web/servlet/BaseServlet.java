package com.lyf.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.xml.ws.Service;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet,根据请求的最后一段路径来进行方法分发
 */

public class BaseServlet extends HttpServlet {

    //根据请求的最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求路径
        String uri = req.getRequestURI(); // /brand-case/brand/selectAll
//        System.out.println(uri);
        //2. 获取最后一段路径，方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1); //  /selectAll? selectAll?

       System.out.println("methodName: "+methodName);

        //2. 执行方法
        //2.1 获取BrandServlet /UserServlet 字节码对象 Class
        //谁调用我（this 所在的方法） 我(this)代表谁
        //System.out.println(this);  //this代表BrandServlet
        //System.out.println(this);

        Class<? extends BaseServlet> cls = this.getClass();
        //2.2 获取方法 Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3 执行方法
            method.invoke(this,req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
