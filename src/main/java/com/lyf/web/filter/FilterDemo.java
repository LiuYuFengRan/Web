//package com.lyf.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class FilterDemo implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        //1.放行前，对request数据进行处理
//        System.out.println("FilterDemo……");
//
//        //放行
//        filterChain.doFilter(request,response);
//
//        //2.放行后，对response进行数据处理
//        // 资源响应完之后response会有数据
//        System.out.println("3.Filterdemo");
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
