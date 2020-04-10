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
    <link rel="shortcut icon" href="favicon.jpg">
    <title>Tiny Market</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/site.css">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="css/shoppingcar.css">
    <script>
        $(function () {
            <c:if test="${sessionScope.isLogin == null}">
                alert("请先登录");
                window.location.href = "./login.jsp";
            </c:if>
            <c:if test="${sessionScope.isLogin != null}">
                // 界面刷新加载用户购物车内商品
                $.ajax({
                    type:"POST",
                    url:"/shoppingCarController.do?method=loadShoppingCar",
                    dataType:"json",
                    success: function (msg) {
                        loadShoppingCar(msg.data);
                        console.log(msg.data);
                    },
                    error:function (msg) {
                        alert("商品加载失败");
                        console.log(msg.data);
                    }
                });
            </c:if>
        });

        // 加号点击事件监听
        function on_jia(obj) {
            var count = parseInt($(obj).next().html());
            var pid = parseInt($(obj).next().attr("rel"));
            console.log(pid);

            $.ajax({
                type:"POST",
                url:"/shoppingCarController.do?method=increaseCount",
                data:{
                  pid:pid
                },
                success: function (msg) {
                    if (parseInt(msg) == 200){
                        console.log("商品增加成功");
                        window.location.reload();
                    }else {
                        console.log("商品增加失败");
                    }
                },
                error:function (msg) {
                    alert("发现未知错误");
                }
            });

        }

        // 减号点击事件监听
        function on_jian(obj) {
            var count = parseInt($(obj).prev().html());
            var pid = parseInt($(obj).prev().attr("rel"));
            console.log(pid);

            $.ajax({
                type:"POST",
                url:"/shoppingCarController.do?method=decreaseCount",
                data:{
                    pid:pid,
                    count:count
                },
                success: function (msg) {
                    if (parseInt(msg) == 200){
                        console.log("商品减少成功");
                        window.location.reload();
                    }else {
                        console.log("商品减少失败");
                    }
                },
                error:function (msg) {
                    alert("发现未知错误");
                }
            });

        }

        // 下单事件
        function xiadan() {
            var re = new RegExp("[0-9]{1,10}");

            var money = $(".total").html().toString();
            var s = re.exec(money);
            var r = confirm("您共计消费" + s + "元");
            if (r){
                alert("下单成功");
            }else {
                alert("取消下单");
            }
        }

        // 加载购物车
        function loadShoppingCar(products){
            // 模拟返回

            var obj_arr = products;
            // 不喜欢啥颜色删了就行
            var color = ["table-info" ,"table-dark", "table-primary", "table-secondary", "table-success", "table-danger"]



            console.log(obj_arr);
            var total = 0;

            for(var i=0; i<obj_arr.length; i++){
                if (obj_arr[i].count > 0) {
                    var jine = obj_arr[i].price * obj_arr[i].count + "元";
                    var danjia = obj_arr[i].price + "元/个";
                    var shuliang = obj_arr[i].count + "个";
                    total += obj_arr[i]["price"] * obj_arr[i]["count"];
                    $("tbody").append("<tr class='" + color[i % color.length] + "'>" +
                        "<td><img src='" + obj_arr[i].imgurl + "' style='width: 80px; height: 80px'></td>" +
                        "<td>" + obj_arr[i].pname + "</td>" +
                        "<td>" + danjia + "</td>" +
                        "<td rel='" + obj_arr[i].pid + "'>" +
                        "<button class='jiahao' onclick='on_jia(this)'>+</button>" +
                        "<span id='count' rel='" + obj_arr[i].pid + "'>" + shuliang + "</span>" +
                        "<button class='jianhao' onclick='on_jian(this)'>-</button>" +
                        "</td>" +
                        "<td>" + jine + "</td>" +
                        "<td><a href='/shoppingCarController.do?method=removeProduct&pid=" + obj_arr[i].pid + "'>删除</a></td></tr>");
                }
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
                <a href="/usersController.do?method=userExit" style="text-decoration: none;"><span class="btn btn-outline-danger my-2 my-sm-0 ml-2 mt-5">退出登录</span></a>
            </form>
        </c:if>
    </div>
</nav>

<main class="container" role="main" style="margin-top: 5%;"></main>

<div class="back">
    <div class="container">
        <div class="title">
            <div class="main_title text-center">我的购物车</div>
        </div>
        <div class="main_car">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>商品图片</th>
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
            <button class="bsubmit" onclick="xiadan()">我要下单</button>
        </div>
    </div></div>

</body>
<script src="js/changeCount.js"></script>
</html>
