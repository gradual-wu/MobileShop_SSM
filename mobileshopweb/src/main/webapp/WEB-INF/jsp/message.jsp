<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/10/30
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>全局消息显示</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-slider.css" type="text/css">
    <meta http-equiv="refresh" content="3;url=${requestScope.map.url}">
    <!-- jQuery and Modernizr-->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
    <style>
        .Message {
            width: 500px;
            height: 500px;
            position: relative;
            left: 50%;
            margin-left: -250px;
        }
    </style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="Message">
    <h2>${requestScope.map.message}</h2>
    三秒后将自动跳转,如果<span>3</span>秒后没有跳转,请点击<a href="${requestScope.map.url}" class="label-danger">这里</a>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>