<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/19
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="myel" uri="/myel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>MobileShop后台管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Add custom CSS here -->
    <link href="${pageContext.request.contextPath}/css/mobileshop-admin.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
    <!-- Page Specific CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/morris-0.4.3.min.css">
</head>

<body>

<div id="wrapper">
    <!-- Sidebar -->
    <jsp:include page="top.jsp"></jsp:include>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1>数据<small>总览</small></h1>
                <ol class="breadcrumb">
                    <li class="active"><i class="fa fa-dashboard"></i>数据总览</li>
                </ol>
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    欢迎来到MobileShop管理员后台
                </div>
            </div>
        </div><!-- /.row -->

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <i class="fa fa-user fa-5x"></i>
                            </div>
                            <div class="col-xs-6 text-right">
                                <p class="announcement-heading">${requestScope.admin.userSum}</p>
                                <p class="announcement-text">用户总数</p>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/userManager.admin">
                        <div class="panel-footer announcement-bottom">
                            <div class="row">
                                <div class="col-xs-6">
                                    用户管理
                                </div>
                                <div class="col-xs-6 text-right">
                                    <i class="fa fa-arrow-circle-right"></i>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <i class="fa fa-check-square-o fa-5x"></i>
                            </div>
                            <div class="col-xs-6 text-right">
                                <p class="announcement-heading">${requestScope.admin.orderSum}</p>
                                <p class="announcement-text">订单总量</p>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/ordersManager.admin">
                        <div class="panel-footer announcement-bottom">
                            <div class="row">
                                <div class="col-xs-6">
                                    订单管理
                                </div>
                                <div class="col-xs-6 text-right">
                                    <i class="fa fa-arrow-circle-right"></i>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <i class="fa fa-tasks fa-5x"></i>
                            </div>
                            <div class="col-xs-6 text-right">
                                <p class="announcement-heading">${requestScope.admin.productSum}</p>
                                <p class="announcement-text">商品总数</p>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/productManager.admin">
                        <div class="panel-footer announcement-bottom">
                            <div class="row">
                                <div class="col-xs-6">
                                    商品管理
                                </div>
                                <div class="col-xs-6 text-right">
                                    <i class="fa fa-arrow-circle-right"></i>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <i class="fa fa-yen fa-5x"></i>
                            </div>
                            <div class="col-xs-6 text-right">
                                <p class="announcement-heading">${myel:priceTwoDecimal(requestScope.admin.paymentSum)}</p>
                                <p class="announcement-text">总交易额</p>
                            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0)">
                        <div class="panel-footer announcement-bottom">
                            <div class="row">
                                <div class="col-xs-6">

                                </div>
                                <div class="col-xs-6 text-right">
                                    <i class="fa fa-arrow-circle-right"></i>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div><!-- /.row -->
    </div><!-- /#page-wrapper -->

</div><!-- /#wrapper -->
<!-- JavaScript -->
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Page Specific Plugins -->    <script src="${pageContext.request.contextPath}/js/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/js/morris-0.4.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/morris/chart-data-morris.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesorter/jquery.tablesorter.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesorter/tables.js"></script>
<script>
    $(document).ready(function () {
        $(".now-choose > li").eq(0).addClass("active");
    });
</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
