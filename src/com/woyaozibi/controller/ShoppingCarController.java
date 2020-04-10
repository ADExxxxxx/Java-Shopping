package com.woyaozibi.controller;

import com.alibaba.fastjson.JSONObject;
import com.woyaozibi.po.Products;
import com.woyaozibi.po.Shoppingcar;
import com.woyaozibi.service.ShoppingCarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/shoppingCarController.do")
public class ShoppingCarController extends HttpServlet {

    private ShoppingCarService shoppingCarService = new ShoppingCarService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        switch (method){
            // �����Ʒ�����ﳵ
            case "addProduct":
                AddProduct(request, response);
                break;

            // ��ȡ���ﳵ������Ʒ
            case "loadShoppingCar":
                LoadShoppingCar(request, response);
                break;

            // ������Ʒ����
            case "increaseCount":
                IncreaseCount(request, response);
                break;
            // ������Ʒ����
            case  "decreaseCount":
                DecreaseCount(request, response);
                break;

            // ɾ����Ʒ
            case  "removeProduct":
                RemoveProduct(request, response);
                break;

            default:
                System.out.println("URL���ʴ���");

        }

    }

    // �����Ʒ�����ﳵ
    private void AddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            HttpSession session = request.getSession();

            Products products = new Products();
            products.setPid(Integer.parseInt(session.getAttribute("id").toString()));
            products.setPrice(session.getAttribute("price").toString());
            products.setPname(session.getAttribute("pname").toString());
            products.setImgurl(session.getAttribute("imgurl").toString());

            shoppingCarService.addProduct(products);

            response.sendRedirect("shoppingcar.jsp");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // ��ȡ���ﳵ��������Ʒ
    private void LoadShoppingCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            List<Shoppingcar> shoppingcarList = shoppingCarService.getShoppingCar();

            JSONObject obj = new JSONObject();

            obj.put("data", shoppingcarList);
            String str = obj.toJSONString();

            System.out.println("���Ե�<��ȡ���ﳵ������Ʒ> : " + str);

            PrintWriter print = response.getWriter();

            print.write(str);
            print.flush();
            print.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // ���ӹ��ﳵ��Ʒ����
    private void IncreaseCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            request.setCharacterEncoding("UTF-8");

            int pid = Integer.parseInt(request.getParameter("pid"));
            int result = shoppingCarService.IncreaseProduct(pid);
            PrintWriter print = response.getWriter();
            if (result != 0){
                System.out.println("��ӳɹ�");
                print.write("200");
            }
            else{
                System.out.println("���ʧ��");
                print.write("500");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // ���ٹ��ﳵ��Ʒ����
    private void DecreaseCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            request.setCharacterEncoding("UTF-8");

            int pid = Integer.parseInt(request.getParameter("pid"));
            int count = Integer.parseInt(request.getParameter("count"));

            System.out.println("pid" + pid + "\tcount:" + count);

            int result = shoppingCarService.DecreaseCount(pid, count);
            PrintWriter print = response.getWriter();
            if (result != 0){
                System.out.println("���ٳɹ�");
                print.write("200");
            }
            else{
                System.out.println("����ʧ��");
                print.write("500");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // ɾ�����ﳵ��Ʒ
    private void RemoveProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            request.setCharacterEncoding("UTF-8");

            int pid = Integer.parseInt(request.getParameter("pid"));
            int result = shoppingCarService.RemoveProduct(pid);
            if (result != 0){
                System.out.println("ɾ���ɹ�");
                response.sendRedirect("shoppingcar.jsp");
            }
            else{
                System.out.println("ɾ��ʧ��");
                response.sendRedirect("shoppingcar.jsp");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
