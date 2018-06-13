package cn.wycclub.dto;

import javax.validation.constraints.*;

/**
 * 注册表单信息
 *
 * @author WuYuchen
 * @create 2018-02-13 22:24
 **/

public class RegisterForm {

    @Pattern(regexp = "^[a-zA-Z0-9]{5,10}$", message = "用户名为5位到10位之间的数字或字母！")
    private String username;

    @Pattern(regexp = "^[a-zA-Z\\d]{6,18}$", message = "密码必须为字母或数字(必须以字母开头)的6-18位!")
    private String password;

    @Pattern(regexp = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$", message = "邮箱格式不对!")
    private String email;

    @Pattern(regexp = "^[a-zA-Z\\d]{6,18}$", message = "密码必须为字母或数字(必须以字母开头)的6-18位!")
    private String repassword;

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
