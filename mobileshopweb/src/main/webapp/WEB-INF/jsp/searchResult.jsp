<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myel" uri="/myel" %>
<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/29
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索结果</title>
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
    <!-- jQuery and Modernizr-->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>

</head>
<body>
<%--top--%>
<jsp:include page="top.jsp"></jsp:include>

<!--//////////////////////////////////////////////////-->
<!--///////////////////Category Page//////////////////-->
<!--//////////////////////////////////////////////////-->
<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div id="main-content" class="col-md-12">
                <c:forEach items="${requestScope.set}" var="c" varStatus="b">
                    <c:if test="${b.count % 4 == 1}">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="products">
                    </c:if>
                    <div class="col-lg-3 col-md-3 col-xs-12">
                        <div class="product">
                            <div class="image"><a href="${pageContext.request.contextPath}/product.do?pid=${c.pid}"><img
                                    src="${pageContext.request.contextPath}/images/product/${c.pid}/main/1.jpg"/></a>
                            </div>
                            <div class="caption">
                                <div class="name"><h3><a
                                        href="${pageContext.request.contextPath}/product.do?pid=${c.pid}">${c.productName}</a>
                                </h3></div>
                                <div class="price">¥${myel:priceTwoDecimal(c.price)}</div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${b.count % 4 == 0 }">
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--footer--%>
<%@include file="footer.jsp" %>
</body>
</html>
