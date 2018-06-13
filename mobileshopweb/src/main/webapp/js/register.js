var flagUsername = 1;
var flagEmail = 1;
var flagPassword = 1;
var flagRePassword = 1;
$(document).ready(function () {
    /*
    * 用户名校验
    * */
    $("#username").blur(function () {
        var str = $(this).val();
        if (str.trim() == "") {
            $(this).val(null);
            return;
        }
        if (str != str.trim()) {
            $(this).parent().addClass("has-error");
            $(".usernameHint").text("错误");
            flagUsername = 1;
            return;
        }
        var ret = /^[a-zA-Z][a-zA-Z0-9_]{5,10}$/;
        if (ret.test(str)) {
            flagUsername = 0;
        } else {
            $(this).parent().addClass("has-error");
            $(".usernameHint").text("错误");
            flagUsername = 1;
            return;
        }

        $.post("user/checkExistByUsername.do", {"username": str}, function (data) {
            if (data == "true") {
                alert("用户名已存在,请使用别的用户名！");
                $("#username").parent().removeClass("has-success");
                $("#username").parent().addClass("has-error");
                $(".usernameHint").text("错误");
                flagUsername = 1;
            } else {
                $("#username").parent().removeClass("has-error");
                $("#username").parent().addClass("has-success");
                $(".usernameHint").text("正确");
                flagUsername = 0;
            }
        },"text")
    });

    /*
    * 邮箱校验
    * */
    $("#email").keyup(function () {
        var str = $(this).val();
        if (str.trim() == "") {
            $(this).val(null);
            return;
        }
        if (str != str.trim()) {
            $(this).parent().addClass("has-error");
            $(".emailHint").text("错误");
            flagEmail = 1;
        }
        var ret = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
        if (ret.test(str)) {
            $(this).parent().removeClass("has-error");
            $(this).parent().addClass("has-success");
            $(".emailHint").text("正确");
            flagEmail = 0;
        } else {
            $(this).parent().addClass("has-error");
            $(".emailHint").text("错误");
            flagEmail = 1;
        }
    });

    $("#password").keyup(function () {
        var str = $(this).val();
        if (str.trim() == "") {
            $(this).val(null);
            return;
        }

        if (str != str.trim()) {
            $(this).parent().addClass("has-error");
            $(".passwordHint").text("错误");
            flagPassword = 1;
        }
        var ret = /^[a-zA-Z\d]{6,18}$/;
        if (ret.test(str)) {
            $(this).parent().removeClass("has-error");
            $(this).parent().addClass("has-success");
            $(".passwordHint").text("正确");
            flagPassword = 0;
        } else {
            $(this).parent().addClass("has-error");
            $(".passwordHint").text("错误");
            flagPassword = 1;
        }
    });

    //确认密码的验证
    $("#repassword").keyup(function () {
        var str_one = $(this).val();
        var str_two = $("#password").val()
        // alert(1);
        if(str_one.trim() == "" || str_two.trim() == "" || str_one == "" || str_two == "") {
            $(this).val(null);
            return;
        }

        if(str_one == str_two) {
            $(this).parent().removeClass("has-error");
            $(this).parent().addClass("has-success");
            $(".repasswordHint").text("正确");
            flagRePassword = 0;
        } else {
            $(this).parent().removeClass("has-success");
            $(this).parent().addClass("has-error");
            $(".repasswordHint").text("错误");
            flagRePassword = 1;
        }
    });
});

function fromClick() {
    if(flagUsername == 0 && flagEmail == 0 && flagPassword ==0 && flagRePassword == 0) {
        var b = window.confirm("已确认好注册信息,确认注册吗?");
        if(b) {
            $("#register_btn").addClass("disabled");
            return true;
        } else {
            return false;
        }
    } else {
        alert("注册表单中有不符合条件的项目,请修改后再次注册!");
        return false;
    }
}

