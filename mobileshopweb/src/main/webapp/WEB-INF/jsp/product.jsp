<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/17
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/myel" prefix="myel"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <title>${requestScope.product.productBean.title}</title>
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

</head>
<body>
<!--top-->
<jsp:include page="top.jsp"></jsp:include>

<!--productPage-->
<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/index.do">主页</a></li>
                    <li><a href="${pageContext.request.contextPath}/category.do">智能手机</a></li>
                    <li><a href="${pageContext.request.contextPath}/category.do?brand=${requestScope.product.productBean.brand}">${requestScope.product.productBean.brand}</a></li>
                    <li><a href="javascript:void(0)">${requestScope.product.productBean.title}</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div id="main-content" class="col-md-8">
                <div class="product">
                    <div class="col-md-6">
                        <div class="image">
                            <img class="image-main" src="${pageContext.request.contextPath}/images/product/${requestScope.product.productBean.pid}/main/1.jpg" />
                            <div class="image-more">
                                <ul class="row" id="image">
                                    <li class="col-lg-3 col-sm-3 col-xs-4">
                                        <a href="javascript:void(0)"><img class="img-responsive" src="${pageContext.request.contextPath}/images/product/${requestScope.product.productBean.pid}/main/1.jpg"></a>
                                    </li>
                                    <li class="col-lg-3 col-sm-3 col-xs-4">
                                        <a href="javascript:void(0)"><img class="img-responsive" src="${pageContext.request.contextPath}/images/product/${requestScope.product.productBean.pid}/main/2.jpg"></a>
                                    </li>
                                    <li class="col-lg-3 col-sm-3 col-xs-4">
                                        <a href="javascript:void(0)"><img class="img-responsive" src="${pageContext.request.contextPath}/images/product/${requestScope.product.productBean.pid}/main/3.jpg"></a>
                                    </li>
                                    <li class="col-lg-3 col-sm-3 col-xs-4">
                                        <a href="javascript:void(0)"><img class="img-responsive" src="${pageContext.request.contextPath}/images/product/${requestScope.product.productBean.pid}/main/4.jpg"></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caption">
                            <div class="name"><h3>${requestScope.product.productBean.title}</h3></div>
                            <div class="info">
                                <ul>
                                    <li style="margin-top: 10px">品牌: ${requestScope.product.productBean.brand}</li>
                                    <li style="margin-top: 10px">商品ID: <span class="pid">${requestScope.product.productBean.pid}</span></li>
                                </ul>
                            </div>
                            <div class="price" style="margin-top: 10px;"><h4>¥${myel:priceTwoDecimal(requestScope.product.productBean.price)}</h4></div>
                            <div class="rating" style="margin-top: 10px">库存:&nbsp; ${requestScope.product.productBean.stock}&nbsp; 件</div>
                            <div class="well" style="margin-top: 30px;">
                                <label>数量: </label>
                                <input class="form-inline quantity" id="quantity" type="text" name="quantity" onchange="checkQuantity(1,this,${requestScope.product.productBean.stock})" value="1">
                                <button class="btn btn-2" onclick="addCart(${requestScope.product.productBean.pid}, ${sessionScope.user.uid})">加入购物车</button>
                                <a href="javascript:void(0)" onclick="clickNowPay()" class="btn btn-danger nowPay">立即购买</a>
                            </div>
                            <c:choose>
                                <c:when test="${requestScope.product.collectionFlag}">
                                    <a href="javascript:void(0)" onclick="removeCollection()" class="btn btn-success" style="margin-top: 10px;">商品已收藏</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0)" onclick="insertCollection()" class="btn btn-default" style="margin-top: 10px;">点击收藏</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="product-desc">
                    <ul class="nav nav-tabs">
                        <li class="detail active"><a href="#description" data-toggle="tab">商品详情</a></li>
                        <li class="access"><a href="#review" data-toggle="tab">商品评价</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="description" class="tab-pane active">
                            <img src="${pageContext.request.contextPath}/images/product/${requestScope.product.productBean.pid}/desc/1.jpg">
                        </div>
                        <div id="review" class="tab-pane fade">
                            <div>该功能暂未开发!</div>
                        </div>
                    </div>
                </div>
                <div class="product-related">
                    <div class="heading"><h2>相关商品</h2></div>
                    <div class="products">
                        <c:forEach var="radomInfo" items="${requestScope.product.radomProduct}">
                            <div class="col-lg-4 col-md-4 col-xs-12">
                                <div class="product">
                                    <div class="image"><a href="${pageContext.request.contextPath}/product.do?pid=${radomInfo.pid}"><img src="${pageContext.request.contextPath}/images/product/${radomInfo.pid}/main/1.jpg" /></a></div>
                                    <div class="buttons">
                                        <a class="btn cart" href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a>
                                        <a class="btn wishlist" href="#"><span class="glyphicon glyphicon-heart"></span></a>
                                        <a class="btn compare" href="#"><span class="glyphicon glyphicon-transfer"></span></a>
                                    </div>
                                    <div class="caption">
                                        <div class="name"><a href="${pageContext.request.contextPath}/product.do?pid=${radomInfo.pid}">${radomInfo.title}</a></div>
                                        <div class="price">${myel:priceTwoDecimal(radomInfo.price)}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div id="sidebar" class="col-md-4">
                <div class="widget wid-brand">
                    <div class="heading"><h4>品牌</h4></div>
                    <div class="content">
                        <c:forEach items="${sessionScope.brandList}" var="b" >
                            <a class="checkbox" href="${pageContext.request.contextPath}/category.do?brand=${b}">${b}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="widget wid-product">
                    <div class="heading"><h4>最新商品</h4></div>
                    <div class="content">
                        <c:forEach items="${requestScope.product.newProduct}" var="x">
                            <div class="product">
                                <a href="${pageContext.request.contextPath}/product.do?pid=${x.pid}"><img src="${pageContext.request.contextPath}/images/product/${x.pid}/main/1.jpg" /></a>
                                <div class="wrapper">
                                    <font size="2px"><a href="${pageContext.request.contextPath}/product.do?pid=${x.pid}">${x.title}</a></font>
                                    <div class="price">¥${myel:priceTwoDecimal(x.price)}</div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--footer-->
<%@include file="footer.jsp"%>
<script>
    $(document).ready(function () {
        $("#image > li").hover(function () {
            var imgSrc = $(this).find("img").attr("src");
            $(".image-main").attr("src",imgSrc);
        });
    });
    var addCart = function (pid) {
        var quantity = $(".quantity").val();
        if (window.confirm("你确定将该商品加入购物车吗?")) {
            $.ajax({
                url:"${pageContext.request.contextPath}/addCart.do",
                type:"POST",
                data:{"pid":pid,"quantity":quantity},
                success:function(data) {
                    alert(data.message);
                    if (data.url != null && data.url !== "") {
                        urlChange(data.url);
                    }
                },
                dataType:"json"
            })
          }
    };

    var checkQuantity = function(count,obj,stock) {
        var quantity = $(obj).val();
        if(count == quantity) {
            return;
        }
        if(isNaN(quantity) || quantity <= 0) {
            $(obj).val(count);
            return;
        }
        if(quantity > stock) {
            alert("库存不足!!");
            $(obj).val(count);
            return;
        }
    };

    var clickNowPay = function () {
        var quantity = $(".quantity").val();
        var pid = $(".pid").text();
        if (window.confirm("亲,确认立即购买吗?")) {
            $.ajax({
                url:"nowPay.do",
                type:"POST",
                data:{"pid": pid, "quantity": quantity},
                dataType: "JSON",
                success: function (data) {
                    alert(data.message);
                    if (data.flag === "true") {
                        window.location.href = data.url;
                    }
                }
            })
        }
    }

    var removeCollection = function () {
        var pid = $(".pid").text();
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
    };

    var insertCollection = function () {
        var pid = $(".pid").text();
        if (window.confirm("您确认收藏该商品吗？")) {
            $.ajax({
                url:"addCollection.do",
                data:{"pid": pid},
                dataType:"JSON",
                type:"POST",
                success: function (data) {
                    alert(data.message);
                    if (data.flag === "true") {
                        location.reload();
                        if (data.url !== undefined) {
                            window.location.href = data.url;
                        }
                    }
                }

            })
        }
    }
</script>
</body>
</html>
