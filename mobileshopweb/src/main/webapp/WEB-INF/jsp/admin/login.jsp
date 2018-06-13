<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/19
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>管理员后台系统</title>
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
    <style>
        .login-form {
            position: absolute;
            top: 50%;
            width: 280px;
            height:280px;
            left: 50%;
            margin-left: -140px;
            margin-top: -140px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="login-form">
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div class="heading"><h2>管理员登陆</h2></div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="帐号 : " name="username" id="username" maxlength="10" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="密码 : " name="password" id="password" required>
                    </div>
                    <button type="submit" class="btn btn-1" name="login" id="login">登录</button>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- Core JavaScript Files -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
<![endif]-->
<script>
    $("#login").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url:"login-index.admin",
            data:{"username": username, "password": password},
            dataType:"JSON",
            type:"POST",
            success: function(data) {
                alert(data.message);
                if (data.flag == "true") {
                    window.location.href = data.url;
                    return;
                }
                location.reload();
            }
        })
    })
</script>
</body>
</html>
