package com.lyf.web.servlet;

import com.lyf.pojo.User;
import com.lyf.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
   private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //1.获取用户名密码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //获取复选框的数据
        String remember = request.getParameter("remember");


        //2.调用service查询
        User user = service.login(username,password);
        //3.判断
        if(user!=null){
            //登录成功,跳转到查询所有的BrandServlet
            //使用重定向，因为没有数据需要共享
            //判断用户是否勾选了记住我
            if("1".equals(remember)){
                //勾选了，则发送cookie

                //1.创建cookie对象
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);

                //设置cookie的存活时间
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);

                //2.将c_username发送到浏览器客户端
                response.addCookie(c_username);
                response.addCookie(c_password);

            }
            //将登录成功后的user对象，存储到session中
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            String contextPath=request.getContextPath();
            response.sendRedirect(contextPath+"/selectAllServlet");

        }else{
            //登录失败
            //存储错误信息到request
            request.setAttribute("login_msg","用户名或者密码错误");

            //跳转到loginServlet页面,在request中的数据使用转发获取数据
            request.getRequestDispatcher("login.jsp").forward(request,response);


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
