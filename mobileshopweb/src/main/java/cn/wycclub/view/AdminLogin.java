package cn.wycclub.view;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author WuYuchen
 * @Description 管理员登录
 * @Date 2018-03-14 22:03
 */
@Controller
public class AdminLogin {

    @RequestMapping("/login.admin")
    public String toLoginByAdmin() {
        return "/WEB-INF/jsp/admin/login.jsp";
    }

    @RequestMapping(value = "/login-index.admin", method = RequestMethod.POST)
    public void login(HttpSession session, String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        InputStream in = AdminLogin.class.getClassLoader().getResourceAsStream("admin.properties");
        Properties adminConfig = new Properties();
        adminConfig.load(in);
        String adminUsername = adminConfig.getProperty("admin-username");
        String adminPassword = adminConfig.getProperty("admin-password");
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            session.setAttribute("admin", username);
            map.put("message", "登录成功，马上跳转");
            map.put("flag", "true");
            map.put("url", "index.admin");
        } else {
            map.put("message", "用户名或密码错误！");
            map.put("flag", "false");
        }
        String json = JSONObject.toJSONString(map);
        response.getWriter().write(json);
    }

    @RequestMapping("/cancel.admin")
    public String cancel(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:login.admin";
    }
}
