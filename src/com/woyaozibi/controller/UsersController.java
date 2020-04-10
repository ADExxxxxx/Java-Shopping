package com.woyaozibi.controller;

import com.woyaozibi.po.Users;
import com.woyaozibi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/usersController.do")
public class UsersController extends HttpServlet {

    // 用户服务类
    private UserService userService = new UserService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String method = request.getParameter("method");
        switch (method){
            // 用户登录
            case "userLogin":
                UserLogin(request, response);
                break;

            // 用户注册
            case  "userRegist":
                UserRegist(request, response);
                break;

            // 退出登录
            case "userExit":
                USerExit(request, response);
                break;

            default:
                System.out.println("URL访问错误");
        }
    }

    // 用户登录
    private void UserLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("接收到前台数据:\n用户名:"+username+"\n密码:"+password);
        try {
            if (userService.checkUsers(username, password) != null){
                System.out.println("登录成功");
                HttpSession session = request.getSession();
                session.setAttribute("isLogin", true);
                session.setAttribute("username", username);
                response.sendRedirect("index.jsp");
            }
            else{
                System.out.println("登录失败，账户或密码错误");
                response.sendRedirect("login.jsp");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 用户注册
    private void UserRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Users users = new Users(username, password, email, phone);

        System.out.println("接收到前台数据:\n用户名:" + username + "\n密码:" + password);
        try {
            if (userService.InsertUser(users) != 0) {
                System.out.println("注册成功");

                response.sendRedirect("login.jsp");
            } else {
                System.out.println("注册失败");
                response.sendRedirect("register.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 用户退出登录
    private void USerExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("index.jsp");
    }
}
