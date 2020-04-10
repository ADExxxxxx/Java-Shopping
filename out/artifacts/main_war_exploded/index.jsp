<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/2
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tiny Market</title>
  <link rel="stylesheet" href="./css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/site.css">
  <link rel="shortcut icon" href="favicon.jpg">
  <script src="./js/jquery-1.11.3.min.js"></script>
  <script src="./js/bootstrap.bundle.js"></script>
  <script>
      function LoadImg(ProductsArray){
          console.log(ProductsArray.length);
          var rows = -1;
          for (var i=0; i<ProductsArray.length; i++){
            if (i%4 == 0){
                rows = rows + 1;
                var rows_html="<div class='row justify-content-around text-center'></div>"
                $("#Products").append(rows_html);
            }
            var col_html = "<div class=\"col-xl-3 mb-5 text-center card\" style=\"min-height: 450px;\">\n" +
                "      <div class=\"img_box text-center\">\n" +
                "        <a href='./productsController.do?method=loadProductInfo&id="+ ProductsArray[i]["pid"] +"' style='text-decoration: none'><img src="+ ProductsArray[i]["imgUrl"] +" class=\"w-100\" style=\"max-width: 300px;min-width: 200px;max-height:300px;border-radius: 15px;\"></div>\n" +
                "        <p class=\"lead pname text-center\">"+ProductsArray[i]["pname"]+"</p>\n" +
                "        <div class=\"info\"><div class=\"qian\">"+ProductsArray[i]["price"]+"元</div><div class=\"zan\">"+ProductsArray[i]["pdesc"]+"</div></div>\n" +
                "      </div>\n" +
                "    </div>";
            $("#Products>.row").eq(rows).append(col_html);
          }
      }
      $(function () {
        $.ajax({
            type:"POST",
            url:"/productsController.do?method=loadProduct",
            dataType:"json",
            success: function (msg) {
                LoadImg(msg.data)
            },
            error:function (msg) {
                alert("图片加载失败")
            }
        });
        $("#Products > p").css("margin-left", "10px");
      })

      function closePoster(obj) {
          $(obj).parent().parent().parent().parent().css("display", "none");
      }
  </script>
</head>
<body>


<div style="z-index:100;display: block; position: fixed; left: 20px; top: 270px;">
  <div id="zxd_x">
    <div style="width: 150px; height: 440px;">
      <div class="content">
        <a href=""><img src="./img/poster.jpg"></a>
      </div>
      <div>
        <span class="close" onclick="closePoster(this)">点击关闭</span>
      </div>
    </div>

  </div>
</div>
<div style="z-index:100;display: block; position: fixed; right: 130px; top: 270px;">
  <div id="zxd_x">
    <div style="width: 150px; height: 440px;">
      <div class="content">
        <a href=""><img src="./img/poster.jpg"></a>
      </div>
      <div>
        <span class="close" onclick="closePoster(this)">点击关闭</span>
      </div>
    </div>

  </div>
</div>

<img src="./zoumadeng.gif" style="width: 100%; margin-top: 5%;">

<!--导航栏-->
<nav class="navbar navbar-expand-xl navbar-dark fixed-top bg-dark">
  <a class="navbar-brand" href="#">My Market</a>
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
        <a class="nav-link" href="#">
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
        <a href="./shoppingcar.jsp" style="text-decoration: none;"><span class="btn btn-outline-primary my-2 my-sm-0 ml-5 mt-5">购物车</span></a>
        <a href="#" style="text-decoration: none;"><span class="btn btn-outline-success my-2 my-sm-0 ml-2 mt-5">个人中心</span></a>
        <a href="/usersController.do?method=userExit" style="text-decoration: none;"><span class="btn btn-outline-danger my-2 my-sm-0 ml-2 mt-5">退出登录</span></a>
      </form>
    </c:if>
  </div>
</nav>
<!--轮播图-->
<div class="container" style="margin-top: 10%; width: 80%">
  <div class="carousel slide" data-ride="carousel" data-interval="2000" id="myCarousel">
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>

    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="./img/c1.jpg" alt="" class="d-block w-100">
      </div>
      <div class="carousel-item">
        <img src="./img/c2.jpg" alt="" class="d-block w-100">
      </div>
    </div>

    <a class="carousel-control-prev" data-slide="prev" href="#myCarousel">
      <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" data-slide="next" href="#myCarousel">
      <span class="carousel-control-next-icon"></span>
    </a>
  </div>
</div>
<!--提示栏-->
<main class="container" role="main" style="margin-top: 5%;">
  <div class="jumbotron">
    <h1>快来买·电商平台</h1>
    <p class="lead">This is a entrance for "My Tiny Market". We have a large variety of products and It's my pleasure if you can buy something here.</p>
    <a class="btn btn-lg btn-primary" href="#" role="button">
      Let's go and view >>
    </a>
  </div>
</main>
<!--商品浏览-->
<main class="container-fluid text-center" id="Products">
  <h2 class="mb-5 tuijian">店长推荐</h2>
</main>
</body>

<script>
    $(window).resize(function(){
        var img_size = $(".carousel-item>img").css("height");
        $(".carousel>a").css("height", img_size);
    });
</script>
</html>
