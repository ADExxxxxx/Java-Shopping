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
            // 添加商品至购物车
            case "addProduct":
                AddProduct(request, response);
                break;

            // 获取购物车所有商品
            case "loadShoppingCar":
                LoadShoppingCar(request, response);
                break;

            // 增加商品数量
            case "increaseCount":
                IncreaseCount(request, response);
                break;
            // 减少商品数量
            case  "decreaseCount":
                DecreaseCount(request, response);
                break;

            // 删除商品
            case  "removeProduct":
                RemoveProduct(request, response);
                break;

            default:
                System.out.println("URL访问错误");

        }

    }

    // 添加商品至购物车
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

    // 获取购物车中所有商品
    private void LoadShoppingCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            List<Shoppingcar> shoppingcarList = shoppingCarService.getShoppingCar();

            JSONObject obj = new JSONObject();

            obj.put("data", shoppingcarList);
            String str = obj.toJSONString();

            System.out.println("测试点<获取购物车所有商品> : " + str);

            PrintWriter print = response.getWriter();

            print.write(str);
            print.flush();
            print.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 增加购物车商品数量
    private void IncreaseCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            request.setCharacterEncoding("UTF-8");

            int pid = Integer.parseInt(request.getParameter("pid"));
            int result = shoppingCarService.IncreaseProduct(pid);
            PrintWriter print = response.getWriter();
            if (result != 0){
                System.out.println("添加成功");
                print.write("200");
            }
            else{
                System.out.println("添加失败");
                print.write("500");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 减少购物车商品数量
    private void DecreaseCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            request.setCharacterEncoding("UTF-8");

            int pid = Integer.parseInt(request.getParameter("pid"));
            int count = Integer.parseInt(request.getParameter("count"));

            System.out.println("pid" + pid + "\tcount:" + count);

            int result = shoppingCarService.DecreaseCount(pid, count);
            PrintWriter print = response.getWriter();
            if (result != 0){
                System.out.println("减少成功");
                print.write("200");
            }
            else{
                System.out.println("减少失败");
                print.write("500");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 删除购物车商品
    private void RemoveProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            request.setCharacterEncoding("UTF-8");

            int pid = Integer.parseInt(request.getParameter("pid"));
            int result = shoppingCarService.RemoveProduct(pid);
            if (result != 0){
                System.out.println("删除成功");
                response.sendRedirect("shoppingcar.jsp");
            }
            else{
                System.out.println("删除失败");
                response.sendRedirect("shoppingcar.jsp");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
