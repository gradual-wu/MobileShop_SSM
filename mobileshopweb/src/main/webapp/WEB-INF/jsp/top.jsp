<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/17
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- top -->
<nav id="top">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-xs-6">
                <ul class="top-left">
                    <li class="top_cart_div" style="display: none;">
                        <a href="${pageContext.request.contextPath}/cart.do?uid=${sessionScope.user.uid}" class="btn btn-success"><span
                                class="glyphicon glyphicon-shopping-cart"></span>&nbsp;购物车</a>
                    </li>
                    <c:if test="${sessionScope.user != null}">
                        <li><span class="btn">当前余额 : ¥ ${sessionScope.user.money} &nbsp;</span></li>
                    </c:if>
                </ul>
            </div>
            <div class="col-xs-6 col-sm-6">
                <c:choose>
                    <c:when test="${sessionScope.user==null}">
                        <ul class="top-link">
                            <li><a href="${pageContext.request.contextPath}/login.do" class="btn btn-info"><span
                                    class="glyphicon glyphicon-user"></span>&nbsp;登录</a></li>
                            <li><a href="${pageContext.request.contextPath}/register.do" class="btn btn-danger"><span
                                    class="glyphicon glyphicon-edit"></span>&nbsp;注册</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="top-link">
                            <li><span class="btn">欢迎您 : ${user.username} &nbsp;</span></li>
                            <li><a href="${pageContext.request.contextPath}/toShowOrdersList.do" class="btn btn-info"><span
                                    class="glyphicon glyphicon-user"></span>&nbsp;我的订单</a></li>
                            <li><a href="${pageContext.request.contextPath}/getCollections.do" class="btn btn-info"><span
                                    class="glyphicon glyphicon-user"></span>&nbsp;我的收藏</a></li>
                            <li><a href="javascript:void(0)" onclick="userCancel()" class="btn btn-danger"><span
                                    class="glyphicon glyphicon-asterisk"></span>&nbsp;注销</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
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
            <%--<form class="form-search" onsubmit="return searchCheck()" action="${pageContext.request.contextPath}/SearchProduct.do" method="post">
                <input maxlength="20" type="text" class="input-medium search-query search_product" name="search">
                <button type="submit" class="btn"><span class="glyphicon glyphicon-search"></span></button>
            </form>--%>
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
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="${pageContext.request.contextPath}/category.do">智能手机</a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${sessionScope.brandList}" var="brand">
                            <li><a href="${pageContext.request.contextPath}/category.do?brand=${brand}">${brand}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
