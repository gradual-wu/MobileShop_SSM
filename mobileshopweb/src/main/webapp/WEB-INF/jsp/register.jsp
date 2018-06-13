<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/10/20
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户注册</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-slider.css" type="text/css">
    <!-- jQuery and Modernizr-->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
</head>
<body>
<%--top--%>
<jsp:include page="top.jsp"></jsp:include>
<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/index.do">主页</a></li>
                    <li><a href="${pageContext.request.contextPath}/register.do">注册</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="register_form">
    <div class="row">
        <div class="col-md-12 col-xs-12 col-lg-12">
            <div class="heading"><h2>用户注册</h2></div>
            <form name="form2" id="form2" method="post" action="${pageContext.request.contextPath}/user/register.do" onsubmit="return fromClick()">
                <div class="form-group has-feedback">
                    <div class="input-group">
                        <input type="text" class="form-control required" placeholder="用户名 : 5-10位字母,下划线或数字,首字母必须为字母!!"
                               name="username" id="username" required maxlength="10" pattern=[a-zA-z0-9]{5,10}>
                        <span class="input-group-addon usernameHint" role="alert"><span
                                class="glyphicon glyphicon-pencil"></span></span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <div class="input-group">
                        <input type="email" class="form-control required" placeholder="电子邮箱 : " name="email" id="email"
                                required>
                        <span class="input-group-addon emailHint" role="alert"><span
                                class="glyphicon glyphicon-pencil"></span></span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <div class="input-group">
                        <input type="password" class="form-control required" placeholder="密码 : 6-18位字母和数字 !!"
                               name="password" id="password" maxlength="18" pattern=[a-zA-z0-9]{6,18} required>
                        <span class="input-group-addon passwordHint" role="alert"><span
                                class="glyphicon glyphicon-pencil"></span></span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <div class="input-group">
                        <input type="password" class="form-control required" placeholder="确认密码 : 和上述密码一致 !!"
                               name="repassword" id="repassword" maxlength="18" pattern=[a-zA-z0-9]{6,18} required>
                        <span class="input-group-addon repasswordHint" role="alert"><span
                                class="glyphicon glyphicon-pencil"></span></span>
                    </div>
                </div>
                <button type="submit" class="btn btn-1" id="register_btn">立刻注册</button>
            </form>
        </div>
    </div>
</div>
<%--footer--%>
<%@include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>
