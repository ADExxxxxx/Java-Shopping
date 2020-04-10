<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/3
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="favicon.jpg">
    <title>Document</title>
    <link rel="shortcut icon" href="favicon.jpg">
    <link rel="stylesheet" href="css/register.css">
</head>
<body>

<form action="/usersController.do">
    <div class="login-container">

        <div class="left-container">
            <div class="title"><span>注册</span></div>
            <div class="input-container">
                <input type="text" name="username" placeholder="用户名">
                <input type="password" name="password" placeholder="密码">
                <input type="email" name="email" placeholder="邮箱">
                <input type="text" name="phone", class="phone" placeholder="电话号码">
                <input type="hidden" name="method" value="userRegist">
            </div>
            <div class="message-container">
                <span>忘记密码</span>
            </div>
        </div>

        <div class="right-container">
            <div class="register-container">
                <span class="register"><a href="login.jsp">登录</a></span>
            </div>
            <div class="action-container">
                <input type="submit" value="提交">
            </div>
        </div>

    </div>
</form>

</body>
</html>
