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

    // �û�������
    private UserService userService = new UserService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String method = request.getParameter("method");
        switch (method){
            // �û���¼
            case "userLogin":
                UserLogin(request, response);
                break;

            // �û�ע��
            case  "userRegist":
                UserRegist(request, response);
                break;

            // �˳���¼
            case "userExit":
                USerExit(request, response);
                break;

            default:
                System.out.println("URL���ʴ���");
        }
    }

    // �û���¼
    private void UserLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("���յ�ǰ̨����:\n�û���:"+username+"\n����:"+password);
        try {
            if (userService.checkUsers(username, password) != null){
                System.out.println("��¼�ɹ�");
                HttpSession session = request.getSession();
                session.setAttribute("isLogin", true);
                session.setAttribute("username", username);
                response.sendRedirect("index.jsp");
            }
            else{
                System.out.println("��¼ʧ�ܣ��˻����������");
                response.sendRedirect("login.jsp");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // �û�ע��
    private void UserRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Users users = new Users(username, password, email, phone);

        System.out.println("���յ�ǰ̨����:\n�û���:" + username + "\n����:" + password);
        try {
            if (userService.InsertUser(users) != 0) {
                System.out.println("ע��ɹ�");

                response.sendRedirect("login.jsp");
            } else {
                System.out.println("ע��ʧ��");
                response.sendRedirect("register.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // �û��˳���¼
    private void USerExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("index.jsp");
    }
}
