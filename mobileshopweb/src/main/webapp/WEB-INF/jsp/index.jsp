<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/17
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/myel" prefix="myel"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
    <title>MobileShop</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"  type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


    <!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css"  type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-slider.css" type="text/css">
    <!-- jQuery and Modernizr-->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>


</head>
<body>
<%--top--%>
<jsp:include page="top.jsp"></jsp:include>
<%-- homePage--%>
<div id="page-content" class="home-page">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <!-- Carousel -->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators hidden-xs">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <a href="${pageContext.request.contextPath}/product.do?pid=126" class="main_banner">
                                <img src="${pageContext.request.contextPath}/images/R11.jpg" alt="First slide">
                            </a>
                            <!-- Static Header -->
                            <div class="header-text hidden-xs">
                                <div class="col-md-12 text-center">
                                </div>
                            </div><!-- /header-text -->
                        </div>
                        <div class="item">
                            <a href="${pageContext.request.contextPath}/product.do?pid=67" class="main_banner">
                                <img src="${pageContext.request.contextPath}/images/IPhone7.jpg" alt="Second slide">
                            </a>
                            <!-- Static Header -->
                            <div class="header-text hidden-xs">
                                <div class="col-md-12 text-center">
                                </div>
                            </div><!-- /header-text -->
                        </div>
                        <div class="item">
                            <a href="${pageContext.request.contextPath}/product.do?pid=1" class="main_banner">
                                <img src="${pageContext.request.contextPath}/images/Honor9.jpg" alt="Third slide">
                            </a>
                            <!-- Static Header -->
                            <div class="header-text hidden-xs">
                                <div class="col-md-12 text-center">
                                </div>
                            </div><!-- /header-text -->
                        </div>
                    </div>
                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div><!-- /carousel -->
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="heading"><h2>手机推荐</h2></div>
                <div class="products">
                    <c:forEach var="groom" items="${requestScope.radomProductListGroom}">
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="product">
                                <div class="image"><a href="${pageContext.request.contextPath}/product.do?pid=${groom.pid}"><img src="${pageContext.request.contextPath}/images/product/${groom.pid}/main/1.jpg"/></a></div>

                                <div class="caption">
                                    <div class="name"><h3><a href="${pageContext.request.contextPath}/product.do?pid=${groom.pid}">${groom.title}</a></h3></div>
                                    <div class="price">¥${myel:priceTwoDecimal(groom.price)}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="banner">
                <div class="col-sm-6">
                    <img src="${pageContext.request.contextPath}/images/sub-banner5.png" />
                </div>
                <div class="col-sm-6">
                    <img src="${pageContext.request.contextPath}/images/sub-banner4.png" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="heading"><h2>猜你喜欢</h2></div>
                <div class="products">
                    <c:forEach var="like" items="${requestScope.radomProductListLike}">
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="product">
                                <div class="image"><a href="${pageContext.request.contextPath}/product.do?pid=${like.pid}"><img src="${pageContext.request.contextPath}/images/product/${like.pid}/main/1.jpg"/></a></div>
                                <div class="caption">
                                    <div class="name"><h3><a href="${pageContext.request.contextPath}/product.do?pid=${like.pid}">${like.title}</a></h3></div>
                                    <div class="price">¥${myel:priceTwoDecimal(like.price)}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%--footer--%>
<%@include file="footer.jsp"%>
</body>
</html>
