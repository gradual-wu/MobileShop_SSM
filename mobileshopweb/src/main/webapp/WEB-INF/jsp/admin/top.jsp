<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/19
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.admin">MobileShop Admin</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav now-choose">
            <li><a href="${pageContext.request.contextPath}/index.admin"><i class="fa fa-dashboard"></i>数据统计</a></li>
            <li><a href="${pageContext.request.contextPath}/userManager.admin"><i class="fa fa-desktop"></i>用户管理</a></li>
            <li><a href="${pageContext.request.contextPath}/ordersManager.admin"><i class="fa fa-table"></i>订单管理</a></li>
            <li><a href="${pageContext.request.contextPath}/productManager.admin"><i class="fa fa-edit"></i>商品管理</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right navbar-user">
            <li style="color: #ffffff;"><a class="time_now"><span></span>年<span></span>月<span></span>日<span></span>时<span></span>分<span></span>秒</a></li>
            <li class="dropdown user-dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>&nbsp;${sessionScope.admin}<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/cancel.admin"><i class="fa fa-power-off"></i>退出</a></li>
                </ul>
            </li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
