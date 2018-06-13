package cn.wycclub.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登陆注册界面显示
 *
 * @author WuYuchen
 * @create 2018-02-13 21:32
 **/

@Controller
public class UserView {

    @RequestMapping("/login.do")
    public String goLogin() {
        return "/WEB-INF/jsp/login.jsp";
    }

    @RequestMapping("/register.do")
    public String goRegister() {
        return "/WEB-INF/jsp/register.jsp";
    }
}
