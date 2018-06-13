<%--
  Created by IntelliJ IDEA.
  User: WuYuchen
  Date: 2017/11/21
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script>
    $(document).ready(function() {
        function time() {
            var date = new Date();
            var n = date.getFullYear();
            var y = date.getMonth()+1;
            var t = date.getDate();
            var h = date.getHours();
            var m = date.getMinutes();
            var s = date.getSeconds();

            $('.time_now span').eq(0).html(n);
            $('.time_now span').eq(1).html(y);
            $('.time_now span').eq(2).html(t);
            $('.time_now span').eq(3).html(h);
            $('.time_now span').eq(4).html(m);
            $('.time_now span').eq(5).html(s);
            for (var i = 0; i < $('div').length; i++) {
                if ($('div').eq(i).text().length == 1) {
                    $('div').eq(i).html(function(index, html) {
                        return 0 + html;
                    });
                }
            }
        }
        time();
        setInterval(time, 1000);
    });
</script>