package com.woyaozibi.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.woyaozibi.po.Products;
import com.woyaozibi.po.Shoppingcar;
import com.woyaozibi.service.ProductsService;

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

@WebServlet("/productsController.do")
public class ProductsController extends HttpServlet {

    private ProductsService productsService = new ProductsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        switch (method){
            // 加载所有商品
            case "loadProduct":
                LoadProducts(request, response);
                break;

            // 加载单个商品信息
            case "loadProductInfo":
                LoadProductInfo(request, response);
                break;

            default:
                System.out.println("URL访问错误");
                break;
        }

    }

    // 获取所有商品
    private void  LoadProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");

        try {
            List<Products> productsList = productsService.getProducts();
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();

            for (Products p : productsList){
                JSONObject obj = new JSONObject();
                obj.put("pname", p.getPname());
                obj.put("pid", p.getPid());
                obj.put("price", p.getPrice());
                obj.put("imgUrl",p.getImgurl());
                obj.put("pdesc", p.getPdesc());
                jsonArray.add(obj);
            }
            JSONObject result = new JSONObject();
            result.put("data", jsonArray);

            String r_str = result.toJSONString();

            PrintWriter print = response.getWriter();
            print.write(r_str);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取商品信息
    private void LoadProductInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        System.out.println(id);

        try {
            Products productsInfo= productsService.getProductInfo(Integer.parseInt(id));

            HttpSession session = request.getSession();
            session.setAttribute("pname", productsInfo.getPname());
            session.setAttribute("imgurl", productsInfo.getImgurl());
            session.setAttribute("pdesc", productsInfo.getPdesc());
            session.setAttribute("price", productsInfo.getPrice());
            session.setAttribute("id", productsInfo.getPid());

            response.sendRedirect("productInfo.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



