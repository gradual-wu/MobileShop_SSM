<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/10/27
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/realia-blue.css">


    <!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-slider.css" type="text/css">
    <meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/index.do">
</head>
<body>
<jsp:include page="../top.jsp"></jsp:include>
    <div id="content">
        <div class="container">
            <div id="main">
                <div class="not-found">
                    <strong>403</strong>
                    <h1>您没有足够的权限访问该页面,该页面将在3秒后返回主页!敬请谅解</h1>
                    <hr>
                </div>
            </div>
        </div>
    </div>
<%@include file="../footer.jsp"%>
</body>
</html>
