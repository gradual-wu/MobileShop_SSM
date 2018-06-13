package cn.wycclub.controller;

import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.LoginForm;
import cn.wycclub.dto.RegisterForm;
import cn.wycclub.service.BusinessCollectionService;
import cn.wycclub.service.BusinessUserService;
import cn.wycclub.util.ContextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关处理器
 *
 * @author WuYuchen
 * @create 2018-02-13 21:42
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BusinessUserService service;

    private BusinessCollectionService collectionService;

    private Logger logger = Logger.getLogger(UserController.class);

    /**
     * 检查用户名是否存在
     * */
    @RequestMapping(value = "/checkExistByUsername.do", method = RequestMethod.POST)
    public void checkExistByUsername(HttpServletResponse response, String username) throws Exception {
        logger.debug("检查用户名：" + username + "是否存在");
        boolean flag = service.findLikeByUsername(username);
        logger.debug("用户名是否存在：" + flag);
        response.getWriter().write(String.valueOf(flag));
    }

    /**
     * 用户登录
     * */
    @RequestMapping("/login.do")
    public void login(LoginForm loginForm, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
        logger.debug("用户访问login.do");
        Map<String, String> map = new HashMap<>();
        logger.debug("正在通过service查询用户名和密码是否匹配");
        UserInfo userInfo = service.login(loginForm.getUsername(), loginForm.getPassword());
        if (userInfo == null) {
            logger.debug("用户名和密码不匹配,给用户提示友好信息");
            map.put("message", "用户名或密码不存在");
            map.put("flag", "false");
        } else {
            logger.debug("用户名和密码匹配,将用户信息储存到session中");
            if (userInfo.getState() > 0) {
                map.put("message", "您的账号因违反本网站规定已被封禁，暂时无法登录，请联系管理员！");
                map.put("flag", "true");
            } else {
                session.setAttribute("user", userInfo);
                logger.debug("将用户登录信息通过Cookie发送给客户机");
                Cookie cookie = getCookie(loginForm.getAutoLoginTime() * 24 * 60 * 60, userInfo);
                cookie.setPath(session.getServletContext().getContextPath());
                response.addCookie(cookie);
                logger.debug("为用户跳转到信息显示页面,并做跳转");
                map.put("message", "登录成功,正在跳转!");
                map.put("flag", "true");
            }
            map.put("url", request.getContextPath() + "/index.do");
        }
        logger.debug("返回JSON数据给页面AJAX处理");
        String json = JSONObject.toJSONString(map);
        response.getWriter().write(json);
    }

    /**
     * 生成自动登陆的Cookie
     * autologin=username:expirestime:md5(password+expirestime)
     * */
    protected Cookie getCookie(Integer time, UserInfo userInfo) {
        logger.debug("生成用户登录失效时间");
        long expiresttime = System.currentTimeMillis() + time;
        logger.debug("生成特殊自动登录MD5字段");
        String md5 = ContextUtils.textToMd5(userInfo.getPassword() + ":" + expiresttime + ":" + userInfo.getUsername());
        String cookieValue = userInfo.getUsername() + ":" + expiresttime + ":" + md5;
        logger.debug("生成cookie");
        Cookie cookie = new Cookie("autoLogin",cookieValue);
        logger.debug("设置cookie失效时间");
        cookie.setMaxAge(time);
        return cookie;
    }

    /**
     * 用户注册
     * */
    @RequestMapping("/register.do")
    public ModelAndView register(@Validated RegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        Map<String, String> map = new HashMap<>();
        if (service.register(registerForm)) {
            map.put("message", "注册成功,正在跳转到登陆界面!");
            map.put("url", request.getContextPath() + "/user/login.do");
        } else {
            map.put("message", "注册失败,请重新注册!");
            map.put("url", request.getContextPath() + "/user/register.do");
        }
        mv.addObject("map", map);
        mv.setViewName("/WEB-INF/jsp/message.jsp");
        return mv;

    }

    /**
     * 用户注销
     * */
    @RequestMapping("/cancel.do")
    public void userCancel(HttpSession session, HttpServletResponse response) throws IOException {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null) {
            logger.debug("正在注销用户");
            session.removeAttribute("user");

            logger.debug("将客户端保存的Cookie自动登陆信息覆盖");
            Cookie cookie = getCookie(-1, user);
            cookie.setPath(session.getServletContext().getContextPath());
            response.addCookie(cookie);
        }

        logger.debug("用户注销完成准备进行跳转");
        Map<String,String> map = new HashMap<>();
        map.put("message", "当前用户已注销,正在为您跳转回主页面!");
        map.put("url", session.getServletContext().getContextPath() + "/index.do");
        String json = JSONObject.toJSONString(map);
        response.getWriter().write(json);
    }
}
