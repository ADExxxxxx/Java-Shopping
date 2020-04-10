<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tiny Market</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/site.css">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="css/productInfo.css">
    <script>
        $(function () {
            var col_html =
                "<div class=\"img_box\">\n"+
                "<img src='${imgurl}'>"+
                "  </div>\n"+
                "<div class=\"info_box\">\n"+
                "  <div class=\"product_name\">${pname}</div>\n"+
                "  <div class=\"product_tuijian\">店长大哥倾情推荐：</div>\n"+
                " <div class=\"product_describe\">${pdesc}</div>\n"+
                " <div class=\"button_box\">\n"+
                "  <div class=\"jiage\">&yen${price}</div>\n"+
                " <a class=\"add_car\" href='/shoppingCarController.do?method=addProduct'>加入购物车></a>\n"+
                "<div class=\"pingjia\">8个人说ok</div>\n"+
                " </div>\n"+
                "  </div>\n";
            $("#Products").append(col_html);
        });

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

<div class="title" id="Products">

</div>


<main class="container-fluid text-center">
    <h4 class="mb-5 xihuan">你还可能喜欢</h4>
    <hr/>
    <div class="row justify-content-around text-center">
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>

            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
    </div>
    <div class="row justify-content-around text-center">
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
    </div>
    <div class="row justify-content-around text-center">
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
        <div class="col-xl-3 mb-5 text-center card">
            <div class="text-center img_box2">
                <img src="img/demo.png" class="w-100" style="max-width: 300px;min-width: 200px;border-radius: 15px;">
                <p class="lead text-center pname">一个橙子</p>
                <div class="info"><div class="qian">&yen1554</div><div class="zan">8人说好</div></div>
            </div>
        </div>
    </div>
</main>


</body>

</html>
