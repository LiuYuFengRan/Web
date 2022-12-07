package com.lyf.web.servlet;

import com.lyf.pojo.User;
import com.lyf.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
   private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
       //1.获取用户名及密码
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        //获取用户输入的验证码
        String checkCode=request.getParameter("checkCode");

        //程序生成的验证码，从session获取
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        //比对
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            //不允许注册
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("register.jsp").forward(request,response);

            return;
        }



        //2.调用service注册
        boolean flag = service.register(user);
        //3.判断注册成功与否
        if(flag){
            //注册成功，跳转到登录页面
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else{
            //注册失败，跳转到注册页面
            request.setAttribute("register_msg","用户已存在");
            request.getRequestDispatcher("register.jsp").forward(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
