package cn.wycclub.service;

import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.RegisterForm;

import java.util.List;

public interface BusinessUserService {
    //注册
    boolean register(RegisterForm registerForm) throws Exception;
    //登陆
    UserInfo login(String username, String password) throws Exception;
    //注册时验证用户名是否存在
    boolean findLikeByUsername(String username) throws Exception;
    //自动登录验证用户信息
    List<UserInfo> autoLogin(String username) throws Exception;
    //更新用户信息
    boolean updateUserInfo(UserInfo userInfo);
    //获取用户信息
    UserInfo getUser(Integer id);
}
