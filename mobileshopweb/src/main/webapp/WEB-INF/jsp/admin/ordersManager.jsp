<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/21
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="myel" uri="/myel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>订单管理</title>
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
    <jsp:include page="top.jsp"></jsp:include>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-striped table-hover table-condensed table-bordered col-lg-12" align="center" >
                    <caption>
                        <h2>订单管理</h2>
                    </caption>
                    <thead>
                    <tr bgcolor="#a9a9a9" align="center">
                        <th class="col-lg-1">订单ID</th>
                        <th class="col-lg-2">购买用户</th>
                        <th class="col-lg-3">订单商品</th>
                        <th class="col-lg-1">商品数量</th>
                        <th class="col-lg-2">订单金额(¥)</th>
                        <th class="col-lg-2">添加时间</th>
                        <th class="col-lg-1">订单状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.pageResult.list}" var="order">

                        <c:forEach items="${order.products}" var="product" varStatus="status">
                            <c:choose>
                                <c:when test="${status.first}">
                                    <tr>
                                        <td rowspan="${fn:length(order.products)}" style="text-align: center; height: ${myel:rowHeight(fn:length(order.products), 30)}px; line-height: ${myel:rowHeight(fn:length(order.products), 30)}px">${order.oid}</td>
                                        <td rowspan="${fn:length(order.products)}" style="text-align: center; height: ${myel:rowHeight(fn:length(order.products), 30)}px; line-height: ${myel:rowHeight(fn:length(order.products), 30)}px">${order.username}</td>
                                        <td style="height: 30px; line-height: 30px">
                                            <a href="${pageContext.request.contextPath}/product.do?pid=${product.key.pid}">${product.key.title}</a>
                                        </td>
                                        <td style="height: 30px; line-height: 30px">${product.value}</td>
                                        <td rowspan="${fn:length(order.products)}" style="text-align: center; height: ${myel:rowHeight(fn:length(order.products), 30)}px; line-height: ${myel:rowHeight(fn:length(order.products), 30)}px">￥${order.payment}</td>
                                        <td rowspan="${fn:length(order.products)}" style="text-align: center; height: ${myel:rowHeight(fn:length(order.products), 30)}px; line-height: ${myel:rowHeight(fn:length(order.products), 30)}px">${myel:formatDate(order.createTime)}</td>
                                        <td rowspan="${fn:length(order.products)}" style="text-align: center; height: ${myel:rowHeight(fn:length(order.products), 30)}px; line-height: ${myel:rowHeight(fn:length(order.products), 30)}px">
                                            <c:if test="${order.state == 0}">
                                                <span>待付款</span>
                                            </c:if>
                                            <c:if test="${order.state == 1}">
                                                <span>待收货</span>
                                            </c:if>
                                            <c:if test="${order.state == 2}">
                                                <span>交易成功</span>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td style="height: 30px; line-height: 30px">
                                            <a href="${pageContext.request.contextPath}/product.do?pid=${product.key.pid}">${product.key.title}</a>
                                        </td>
                                        <td>${product.value}</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <c:if test="${myel:checkRow(requestScope.pageResult)}">
            <div class="row text-center">
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${requestScope.pageResult.currentPage != requestScope.pageResult.previousPage}">
                            <li><a href="${pageContext.request.contextPath }/ordersManager.admin?currentPage=${requestScope.pageResult.previousPage}" >上一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="disabled"><a href="javascript:void(0)" >上一页</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="pagenum" items="${requestScope.pageResult.pageBar }">
                        <c:choose>
                            <c:when test="${pagenum==requestScope.pageResult.currentPage }">
                                <li class="active"><a href="javascript:void(0)" class="currentPage">${pagenum }</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageContext.request.contextPath }/ordersManager.admin?currentPage=${pagenum}">${pagenum }</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${requestScope.pageResult.currentPage != requestScope.pageResult.nextPage}">
                            <li><a href="${pageContext.request.contextPath }/ordersManager.admin?currentPage=${pageResult.nextPage}" >下一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="disabled"><a href="javascript:void(0)" >下一页</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </c:if>
    </div>
</div>
<!-- JavaScript -->
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Page Specific Plugins -->
<script src="${pageContext.request.contextPath}/js/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/js/morris-0.4.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/morris/chart-data-morris.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesorter/jquery.tablesorter.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesorter/tables.js"></script>
<script>
    $(document).ready(function () {
        $(".now-choose > li").eq(2).addClass("active");
    });
</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
