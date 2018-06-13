<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/17
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer>
    <div class="container">
        <div class="wrap-footer">
            <div class="row">
                <div class="col-md-3 col-footer footer-1">
                    <img src="${pageContext.request.contextPath}/images/logofooter.png" />
                </div>
                <div class="col-md-3 col-footer footer-2">
                    <div class="heading"><h4>服务与支持</h4></div>
                    <ul>
                        <li><a href="javascript:void(0)">关于我们</a></li>
                        <li><a href="javascript:void(0)">配送信息</a></li>
                        <li><a href="javascript:void(0)">隐私权政策 </a></li>
                        <li><a href="javascript:void(0)">条款及条件</a></li>
                    </ul>
                </div>
                <div class="col-md-3 col-footer footer-3">
                    <div class="heading"><h4>我的账户</h4></div>
                    <ul>
                        <li><a href="javascript:void(0)">我的账户</a></li>
                        <li><a href="javascript:void(0)">品牌</a></li>
                        <li><a href="javascript:void(0)">代金券</a></li>
                    </ul>
                </div>
                <div class="col-md-3 col-footer footer-4">
                    <div class="heading"><h4>联系我们</h4></div>
                    <ul>
                        <li><span class="glyphicon glyphicon-home"></span>China,JiangXi</li>
                        <li><span class="glyphicon glyphicon-earphone"></span>+86 18700000000</li>
                        <li><span class="glyphicon glyphicon-envelope"></span>123@qq.com</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    Copyright &copy; 2017.Gradual All rights reserved.
                </div>
                <div class="col-md-6">
                    <div class="pull-right">
                        <ul>
                            <li><img src="${pageContext.request.contextPath}/images/visa-curved-32px.png" /></li>
                            <li><img src="${pageContext.request.contextPath}/images/paypal-curved-32px.png" /></li>
                            <li><img src="${pageContext.request.contextPath}/images/discover-curved-32px.png" /></li>
                            <li><img src="${pageContext.request.contextPath}/images/maestro-curved-32px.png" /></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- jQuery and Modernizr-->
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<!-- Core JavaScript Files -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
<![endif]-->
<script>
    var searchCheck = function () {
        var searchStr = $(".search_product").val();
        if (searchStr.trim() == "" || searchStr.trim() == null) {
            $(".search_product").animate({'opacity':'0.5'},500,function() {
                animate({'opacity':'1'}),500
            });
            return false;
        }
        return true;

    };
    $(function () {
        var h3_height = $(".top_cart_div").offset().top;
        $(window).scroll(function () {
            var this_scrollTop = $(this).scrollTop();
            if (this_scrollTop > h3_height) {
                $(".top_cart_div").show();
            } else {
                $(".top_cart_div").hide();
            }
        });
    });

    var count = 3;
    var urlChange = function (url) {
        count --;
        if (count === 0) {
            window.location.href = url;
        }
        setTimeout(urlChange(url), 1000);
    };
    
    var userCancel = function () {
        if (window.confirm("您确定注销当前用户吗?注销后将返回主页!")) {
            $.ajax({
                url:"user/cancel.do",
                type:"POST",
                data:{},
                success:function (data) {
                    alert(data.message);
                    urlChange(data.url);
                },
                dataType:"json"
            })
        }
    }
</script>