package cn.wycclub.interceptor;

import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.service.BusinessUserService;
import cn.wycclub.util.ContextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户自动登录
 *
 * @author WuYuchen
 * @create 2018-02-16 17:40
 **/

public class AutoLoginHandleInterceptor implements HandlerInterceptor {

    @Autowired
    private BusinessUserService service;

    private Logger log = Logger.getLogger(AutoLoginHandleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.debug("进入用户自动登陆拦截器preHandle");

        UserInfo user = (UserInfo) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            log.debug("用户" + user.getUsername() + "已登录,自动放行");
            return true;
        }

        log.debug("用户未登录,开始检查Cookie中是否存在自动登录信息");
        Cookie cookies[] = httpServletRequest.getCookies();
        Cookie autoLoginCookie = null;
        for (int i = 0 ; cookies != null && i < cookies.length ; i ++) {
            if ("autoLogin".equals(cookies[i].getName())) {
                log.debug("已找到自动登录Cookie::" + cookies[i].getName());
                autoLoginCookie = cookies[i];
            }
        }

        if (autoLoginCookie == null) {
            log.debug("在Cookie中没有找到自动登录的Cookie信息");
            return true;
        }

        String value[] = autoLoginCookie.getValue().split("\\:");
        if (value.length != 3) {
            log.debug("在Cookie中找到的自动登陆信息不完全");
            return true;
        }

        log.debug("检查用户cookie的有效期");
        Long expiresttime = Long.parseLong(value[1]);
        if (System.currentTimeMillis() > expiresttime) {
            log.debug("用户自动登录失效");
            return true;
        }

        String username = value[0];
        log.debug("获取到用户名:" + username + ",准备进行身份验证");
        List<UserInfo> list = service.autoLogin(username);
        if (list.size() < 1) {
            return true;
        }
        UserInfo userInfo = list.get(0);
        String md5 = ContextUtils.textToMd5(userInfo.getPassword() + ":" + expiresttime + ":" + userInfo.getUsername());
        if (value[2].equals(md5)) {
            log.debug("用户:" + username + "身份信息校验通过,正在自动登陆");
            httpServletRequest.getSession().setAttribute("user", userInfo);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
