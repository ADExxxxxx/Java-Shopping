<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MousseYu
  Date: 2020/4/7
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tiny Market</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/site.css">
    <script src="./js/jquery-1.11.3.min.js"></script>
    <script src="./js/bootstrap.bundle.js"></script>
    <style>
        body{
            background: url(img/ss.jpg);
            background-position: center center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;

        }
        .main_title{
            font-size: 40px;
            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        }
        thead>tr>th{
            background-color: blanchedalmond;
            color: black;
            font-size: 22px;
            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        }
        .total{
            float: left;
            color: red;
            font-size: 28px;
            display: inline;

        }
        tr>td>a{
            color: black;
            text-decoration: none;
        }
        .bsubmit{
            float: right;
            width: 180px;
            height: 60px;
            background-color: rgb(240, 80, 80);
            font-size: 28px;
            line-height: 55px;
            text-align: center;
            border-radius: 10px;
            color: aliceblue;
        }
        .footer{
            width: 100%;
        }
    </style>
    <script>



        $(function () {

            //加载用户购物车内商品
            $.ajax({
                type:"POST",
                url:"/ProductsController.do?methodUrl=loadShoppingCar",
                dataType:"json",
                success: function (msg) {
                    loadShoppingCar(msg);
                    console.log(msg);
                },
                error:function (msg) {
                    alert("商品加载失败");
                    console.log(msg);
                }
            });
        });

        function loadShoppingCar(products){
            // 模拟返回

            var obj_arr = products;
            // 不喜欢啥颜色删了就行
            var color = ["table-info", "table-light" ,"table-dark", "bg-primary", "bg-success", "bg-warning", "bg-danger", "bg-info", "table-primary"]



            console.log(obj_arr);
            var total = 0;

            for(var i=0; i<obj_arr.length; i++){

                var jine = obj_arr[i].price * obj_arr[i].count+ "元";
                var danjia = obj_arr[i].price + "元/个";
                var shuliang = obj_arr[i].count + "个";
                total += obj_arr[i]["price"] * obj_arr[i]["count"];
                $("tbody").append("<tr class='"+ color[i % color.length] +"'><td>"+(i+1)+"</td><td>"+obj_arr[i].pname+"</td><td>"+ danjia +"</td><td>"+ shuliang +"</td><td>" + jine + "</td><td><a href='/ProductsController.do?methodUrl=deleteProduct&pid="+obj_arr[i].pid+"'>删除</a></td></tr>");
            }

            $(".total").html("总价:" + total + "元")
        }

    </script>
</head>
<body>

<!--导航栏-->
<nav class="navbar navbar-expand-xl navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="index.jsp">My Market</a>
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse"
            aria-expanded="true" data-target="#navbarCollapse" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navbarCollapse" class="navbar-collapse collapse show">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">
                    Home
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="productsList.jsp">
                    Products List
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    FAQ
                </a>
            </li>
        </ul>
        <c:if test="${sessionScope.isLogin == null}">
            <form class="form-inline" id="beforeType">
                <a href="login.jsp" style="color:#fff;text-decoration: none;"><span class="btn btn-outline-info my-2 my-sm-0 mt-5" id="btnlogin">登录</span></a>
                <a href="register.jsp" style="text-decoration: none;"><span class="btn btn-outline-success my-2 my-sm-0 ml-5 mt-5" id="btnregister">注册</span></a>
            </form>
        </c:if>
        <c:if test="${sessionScope.isLogin != null}">
            <form class="form-inline" id="afterType">
                <span style="color: ghostwhite; font-size: 24px">Hello ${sessionScope.username}!</span>
                <a href="shoppingcar.jsp" style="text-decoration: none;"><span class="btn btn-outline-primary my-2 my-sm-0 ml-5 mt-5">购物车</span></a>
                <a href="#" style="text-decoration: none;"><span class="btn btn-outline-success my-2 my-sm-0 ml-2 mt-5">个人中心</span></a>
                <a href="UserController.do?methodUrl=exitLogin" style="text-decoration: none;"><span class="btn btn-outline-danger my-2 my-sm-0 ml-2 mt-5">退出登录</span></a>
            </form>
        </c:if>
    </div>
</nav>

<main class="container" role="main" style="margin-top: 5%;"></main>

<div class="back">
    <div class="container">
        <div class="title">
            <div class="main_title text-center">爷的购物车</div>
        </div>
        <div class="main_car">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>商品名</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>金额</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>

        </div>
        <div class="footer">
            <div class="total text-left"></div>
            <button class="bsubmit">爷要结账</button>
        </div>
    </div></div>

</body>
</html>
