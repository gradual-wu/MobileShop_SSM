<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/18
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- jQuery and Modernizr-->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>

    <!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-slider.css" type="text/css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/index.do">主页</a></li>
                    <li><a href="${pageContext.request.contextPath}/login.do">登录</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="login_form">
    <div class="row">
        <div class="col-md-12 col-sm12 col-xs-12">
            <div class="heading"><h2>用户登陆</h2></div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="帐号 : " name="username" id="username" maxlength="10" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码 : " name="password" id="password" required>
                </div>
                <div class="form-group">
                    <input type="radio" id="autoLoginTime" name="autoLoginTime" value="7">一周内自动登录
                </div>
                <button class="btn btn-1" name="login" id="loginButton" >登录</button>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script>
    $("#loginButton").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var autoLoginTime = $('#autoLoginTime:checked').val();
        if (autoLoginTime == null) {
            autoLoginTime = 0;
        }
        $.ajax({
            url:"user/login.do",
            data:{"username":username, "password":password, "autoLoginTime":autoLoginTime},
            type:"POST",
            success:function (data) {
                alert(data.message);
                if (data.flag == "true") {
                    urlChange(data.url);
                }
            },
            dataType:"JSON"
        })
    });
</script>
</body>
</html>
