<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/17
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="/myel" prefix="myel"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <title>购物车</title>
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
    <!-- jQuery and Modernizr-->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
</head>
<body>
<%--top--%>
<jsp:include page="top.jsp"></jsp:include>

<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/index.do">主页</a></li>
                    <li><font color="black">购物车</font></li>
                </ul>
            </div>
        </div>
        <div id="cart-space">
            <c:if test="${empty(requestScope.cart.list)}">
                <h1>亲,您的购物车里没有东西呀!</h1>
            </c:if>
            <c:forEach items="${requestScope.cart.list}" var="cartBean">
                <div class="row">
                    <div class="product well">
                        <div class="col-md-3">
                            <div class="image">
                                <img src="${pageContext.request.contextPath}/images/product/${cartBean.pid}/main/1.jpg" />
                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="caption">
                                <div class="name">
                                    <h3>
                                        <a href="${pageContext.request.contextPath}/product.do?pid=${cartBean.pid}">${cartBean.productInfo.title}</a>
                                    </h3>
                                    <input checked="checked" class="checkbox-cid" type="checkbox" onclick="sumPrice()" value="${cartBean.cid}">
                                </div>
                                <div class="info">
                                    <ul>
                                        <li>商品品牌: ${cartBean.productInfo.brand}</li>
                                        <li>商品ID: ${cartBean.pid}</li>
                                        <li>剩余库存: ${cartBean.productInfo.stock}</li>
                                    </ul>
                                </div>
                                <div class="price">¥${myel:priceTwoDecimal(cartBean.payable)}</div>
                                <label>商品数量: </label> <input maxlength="3" onblur="checkQuantity(${cartBean.quantity},this,${cartBean.productInfo.stock},${cartBean.cid},${cartBean.pid})" class="form-inline quantity" type="text" value="${cartBean.quantity}">
                                <hr>
                                <a href="javascript:void(0)" onclick="removeCart(${cartBean.cid}, this)" class="btn btn-default pull-right">删除</a>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="row">
            <div class="pricedetails">
                <div class="col-md-4 col-md-offset-9">
                    <table>
                        <h6>应付款金额</h6>
                        <tr style="border-top: 1px solid #333;border-bottom: 1px solid #333;">
                            <td><h5>总计</h5></td>
                            <td id="paysum"></td>
                        </tr>
                    </table>
                    <br>
                    <a href="javascript:void(0)" onclick="createOrder()" class="btn btn-1">付款</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%--footer--%>
<%@include file="footer.jsp"%>
<script>

    $(document).ready(function () {
        sumPrice();
    });

    function sumPrice() {
        var sum = 0;
        $(".checkbox-cid").each(function () {
            if ($(this).is(":checked")) {
                sum = sum + parseInt($(this).parent().parent().children(".price").text().substr(1));
            }
        });
        $("#paysum").text("¥" + sum);
    }

    var checkQuantity = function(count,obj,stock,cid,pid) {
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
        if(window.confirm("您确认修改吗?")) {
            $.ajax({
                url:"updateCart.do",
                data:{"cid":cid, "quantity":quantity, "pid":pid},
                type:"POST",
                success:function (data) {
                    alert(data.message);
                    var payable = data.cartBean.payable;
                    $(obj).parent().children(".price").text("¥"+payable.toFixed(2));
                    sumPrice();
                },
                dataType:"JSON"
            });
        }
    };

    var removeCart = function (cid, obj) {
        if (window.confirm("您确认从购物车中删除该商品吗?")) {
            $.ajax({
                url:"removeCart.do",
                data:{"id":cid},
                type:"POST",
                success:function (data) {
                    alert(data.message);
                    $(obj).parent().parent().parent().remove();
                    var cartProductCounts = $(".checkbox-cid").length;
                    if(cartProductCounts <= 0) {
                        $("#cart-space").html("<h1>亲,您的购物车里没有东西呀!</h1>");
                    }
                    sumPrice();
                },
                dataType:"JSON"
            })
        }
    };

    var createOrder = function () {
        var cids = "";
        if(window.confirm("确定购买吗?")) {
            $(".checkbox-cid").each(function () {
                if ($(this).is(":checked")) {
                    cids = cids + "," + $(this).val();
                }
            });
            if (cids === "") {
                alert("您当前没有选择购物车中所要购买的商品，请重新确认后下单！")
                return;
            }
            cids = "[" + cids.substring(1, cids.length) + "]";
            $.ajax({
                url:"createOrders.do",
                type:"POST",
                contentType:"application/json;charset=UTF-8",
                data:JSON.stringify(cids),
                dataType:"JSON",
                success:function (data) {
                    alert(data.message);
                    if (data.flag === "true") {
                        window.location.href = data.url;
                    }
                }
            })
        }
    }
</script>
</body>
</html>
