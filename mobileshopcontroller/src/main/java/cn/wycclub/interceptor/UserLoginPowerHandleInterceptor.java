package cn.wycclub.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户登陆权限检查拦截器
 *
 * @author WuYuchen
 * @create 2018-02-16 10:37
 **/

public class UserLoginPowerHandleInterceptor implements HandlerInterceptor {

    private Logger log = Logger.getLogger(UserLoginPowerHandleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("检测用户访问的内容是否需要用户登录");

        log.debug("判断当前连接的是不是业务类的控制器,防止之后强转报错");
        if (HandlerMethod.class.equals(handler.getClass())) {
            log.debug("获取需要访问的controller");
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();

            log.debug("判断Controller是否实现UserLogin接口");
            if (controller instanceof UserLogin) {
                log.debug("检测当前用户是否登录");
                if (request.getSession().getAttribute("user") == null) {
                    log.debug("用户未登录,准备跳转,判断用户请求方式");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("message", "您当前没有登录,无法进行此操作,将为你跳转到登录界面!");
                    map.put("url", request.getContextPath() + "/login.do");
                    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                        log.debug("当前为ajax请求");
                        map.put("flag", "true");
                        String json = JSONObject.toJSONString(map);
                        response.getWriter().write(json);
                    } else {
                        log.debug("当前为普通请求");
                        request.setAttribute("map", map);
                        request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
                    }
                    return false;
                }
            }

            log.debug("判断Controller是否实现AdminLogin接口");
            if (controller instanceof AdminLogin) {
                log.debug("检测当前管理员是否登录");
                if (request.getSession().getAttribute("admin") == null) {
                    log.debug("管理员未登录，准备跳转到管理员登录界面，判断用户请求方式");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("message", "您当前没有登录,无法进行此操作,将为你跳转到登录界面!");
                    map.put("url", request.getContextPath() + "/login.admin");
                    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                        log.debug("当前为ajax请求");
                        map.put("flag", "true");
                        String json = JSONObject.toJSONString(map);
                        response.getWriter().write(json);
                    } else {
                        log.debug("当前为普通请求");
                        request.setAttribute("map", map);
                        request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
