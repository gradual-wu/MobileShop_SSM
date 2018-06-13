<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/21
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myel" uri="/myel" %>
<html>
<head>
    <title>用户管理</title>
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
                        <h2>用户管理</h2>
                    </caption>
                    <thead>
                    <tr bgcolor="#a9a9a9" align="center">
                        <th class="col-lg-1">用户ID</th>
                        <th class="col-lg-1">用户名称</th>
                        <th class="col-lg-1">用户密码(MD5)(可改)</th>
                        <th class="col-lg-1">用户余额</th>
                        <th class="col-lg-1">电话号码</th>
                        <th class="col-lg-2">通讯地址</th>
                        <th class="col-lg-2">注册时间</th>
                        <th class="col-lg-2">修改时间</th>
                        <th class="col-lg-1">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.pageResult.list}" var="user">
                            <tr>
                                <td>${user.uid}</td>
                                <td>${user.username}</td>
                                <td>${user.password}</td>
                                <td>${myel:priceTwoDecimal(user.money)}</td>
                                <td>${user.tel}</td>
                                <td>${user.address}</td>
                                <td>${myel:formatDate(user.gmtCreate)}</td>
                                <td>${myel:formatDate(user.gmtModified)}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.state == 1}">
                                            <a href="javascript:void(0)" onclick="freeze(${user.uid},0)" class="btn btn-link">解冻用户</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0)" onclick="freeze(${user.uid},1)" class="btn btn-link">冻结用户</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
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
                            <li><a href="${pageContext.request.contextPath }/userManager.admin?currentPage=${requestScope.pageResult.previousPage}" >上一页</a></li>
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
                                <li><a href="${pageContext.request.contextPath }/userManager.admin?currentPage=${pagenum}">${pagenum }</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${requestScope.pageResult.currentPage != requestScope.pageResult.nextPage}">
                            <li><a href="${pageContext.request.contextPath }/userManager.admin?currentPage=${requestScope.pageResult.nextPage}" >下一页</a></li>
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

<!-- Page Specific Plugins -->    <script src="${pageContext.request.contextPath}/js/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/js/morris-0.4.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/morris/chart-data-morris.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesorter/jquery.tablesorter.js"></script>
<script src="${pageContext.request.contextPath}/js/tablesorter/tables.js"></script>
<script>
    $(document).ready(function () {
        $(".now-choose > li").eq(1).addClass("active");
    });
    var freeze = function (uid,state) {
        var message;
        if (state == 0) {
            message = "您确认解封UID为" + uid + "的用户吗?";
        } else {
            message = "您确认封禁UID为" + uid + "的用户吗?";
        }
        if (window.confirm(message)) {
            $.ajax({
                url:"updateUserInfo.admin",
                data:{"uid": uid, "state": state},
                dataType:"JSON",
                type:"POST",
                success: function (data) {
                    alert(data.message);
                    if (data.flag == "true") {
                        location.reload();
                    } else {
                        alert("出了点小问题！")
                    }
                }
            });
        }
    }
</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
