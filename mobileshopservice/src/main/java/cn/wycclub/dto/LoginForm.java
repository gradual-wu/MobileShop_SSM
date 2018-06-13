package cn.wycclub.dto;

/**
 * 登录提交表单
 *
 * @author WuYuchen
 * @create 2018-02-16 11:11
 **/

public class LoginForm {

    protected String username;
    protected String password;
    protected int autoLoginTime;

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

    public int getAutoLoginTime() {
        return autoLoginTime;
    }

    public void setAutoLoginTime(int autoLoginTime) {
        this.autoLoginTime = autoLoginTime;
    }
}
