<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/19
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myel" uri="/myel" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <title>我的账户</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"  type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css"  type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-slider.css" type="text/css">
    <style>
        td,th {
            text-align:center;
            vertical-align:middle;
            overflow: hidden;
        }
        .evaluate {
            position: fixed;
            width: 400px;
            height: 400px;
            left: 50%;
            margin-left: -200px;
            top: 50%;
            padding: 50px;
            margin-top: -200px;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            border:2px solid #393939;
            background: #fff9ff;
            opacity: 0.9;
            display: none;
        }

        .close_btn{
            position: absolute;
            right: 10px;
            top: 10px;
        }
    </style>
</head>

<body>

<nav id="top">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-xs-6">
                <ul class="top-left">
                    <li class="top_cart_div" style="display: none;">
                        <a href="${pageContext.request.contextPath}/cart.do?uid=${sessionScope.user.uid}" class="btn btn-success"><span
                                class="glyphicon glyphicon-shopping-cart"></span>&nbsp;购物车</a>
                    </li>
                    <li><span class="btn">当前余额 : ¥ ${user.money} &nbsp;</span></li>
                </ul>
            </div>
            <div class="col-xs-6 col-sm-6">
                <ul class="top-link">
                    <li><span>欢迎您 : ${user.username} &nbsp;</span></li>
                    <li><a href="${pageContext.request.contextPath}/toShowOrdersList.do" class="btn btn-info"><span
                            class="glyphicon glyphicon-user"></span>&nbsp;我的订单</a></li>
                    <li><a href="javascript:void(0)" onclick="userCancel()" class="btn btn-danger"><span
                            class="glyphicon glyphicon-asterisk"></span>&nbsp;注销</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<!--Header-->
<header class="container">
    <div class="row header_row">
        <div class="col-md-4">
            <div id="logo"><img src="${pageContext.request.contextPath}/images/logo.png"/></div>
        </div>
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <div id="cart"><a class="btn btn-1" href="${pageContext.request.contextPath}/cart.do?uid=${sessionScope.user.uid}"><span
                    class="glyphicon glyphicon-shopping-cart"></span>购物车</a></div>
        </div>
    </div>
</header>
<!--Navigation-->
<nav id="menu" class="navbar">
    <div class="container">
        <div class="navbar-header"><span id="heading" class="visible-xs">导航</span>
            <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-ex1-collapse"><i class="fa fa-bars"></i></button>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/index.do">主页</a></li>
                <li><a href="javascript:void(0)">我的收藏</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="account-info">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/index.do">主页</a></li>
                    <li><a href="javascript:void(0)">我的收藏</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="tab-content col-lg-12">
                <c:choose>
                    <c:when test="${empty(requestScope.collections)}">
                        <div>亲,您还没有收藏过商品呢!</div>
                    </c:when>
                    <c:otherwise>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover table-condensed table-bordered col-lg-12" align="center" >
                                <thead>
                                <tr bgcolor="#a9a9a9" align="center">
                                    <th class="col-lg-2">收藏编号</th>
                                    <th class="col-lg-5">商品名称</th>
                                    <th class="col-lg-2">商品金额</th>
                                    <th class="col-lg-2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.collections}" var="collection">
                                    <tr>
                                        <td>${collection.cid}</td>
                                        <td><a href="${pageContext.request.contextPath}/product.do?pid=${collection.pid}">${collection.title}</a></td>
                                        <td>¥${myel:priceTwoDecimal(collection.price)}</td>
                                        <td><a href="javascript:void(0)" onclick="nowCheck(${collection.pid})">取消收藏</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<%--footer--%>
<%@include file="footer.jsp"%>
<script>
    var nowCheck = function (pid) {
        if (window.confirm("您确认取消收藏该商品吗？")) {
            $.ajax({
                url:"removeCollection.do",
                data:{"pid": pid},
                dataType:"JSON",
                type:"POST",
                success: function (data) {
                    alert(data.message);
                    if (data.flag === "true") {
                        if (data.url !== undefined) {
                            window.location.href = data.url;
                        }
                        location.reload();
                    }
                }

            })
        }
    }
</script>
</body>
</html>
