package cn.wycclub.controller;

import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.CartBean;
import cn.wycclub.dto.CartInfo;
import cn.wycclub.interceptor.UserLogin;
import cn.wycclub.service.BusinessCartService;
import cn.wycclub.vo.Cart;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车处理器
 *
 * @author WuYuchen
 * @create 2018-02-15 1:05
 **/

@Controller
public class CartController implements UserLogin {

    @Autowired
    private BusinessCartService service;

    /**
     * 添加购物车
     * */
    @RequestMapping("/addCart.do")
    public void addCart(CartInfo cartInfo, HttpServletResponse response, HttpServletRequest request) throws Exception {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        Map<String, String> map = new HashMap<>();
        String json;
        cartInfo.setUid(user.getUid());
        service.insertCart(cartInfo);
        map.put("message", "已将商品加入购物车!");
        json = JSONObject.toJSONString(map);
        response.getWriter().write(json);
    }

    /**
     * 进入购物车
     * */
    @RequestMapping("/cart.do")
    public ModelAndView getCart(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = (UserInfo) session.getAttribute("user");
        List<CartBean> list = service.selectCart(user.getUid());
        Cart cart = new Cart();
        cart.setList(list);
        mv.addObject("cart", cart);
        mv.setViewName("/WEB-INF/jsp/cart.jsp");
        return mv;
    }


    /**
     * 更新购物车
     * */
    @RequestMapping("/updateCart.do")
    public void updateCart(CartInfo cartInfo, HttpServletResponse response, HttpSession session) throws Exception {
        System.out.println("update");
        System.out.println("update");
        System.out.println("update");
        System.out.println("update");
        System.out.println("update");
        System.out.println("update");
        UserInfo user = (UserInfo) session.getAttribute("user");
        cartInfo.setUid(user.getUid());
        Map<String, Object> map = new HashMap<>();
        CartBean cartBean = service.updateCart(cartInfo);
        if (cartBean != null) {
            map.put("message", "修改成功!");
            map.put("cartBean", cartBean);
        } else {
            map.put("message", "修改失败!");
        }
        String json = JSONObject.toJSONString(map);
        System.out.println(json);
        response.getWriter().write(json);
    }

    /**
     * 删除购物车中的商品
     * */
    @RequestMapping("/removeCart.do")
    public void removeCart(HttpServletResponse response, Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (service.deleteCart(id)) {
            map.put("message", "已从购物车删除当前商品");
            map.put("flag", "true");
        } else {
            map.put("message", "商品删除失败!");
            map.put("flag", "false");
        }
        String json = JSONObject.toJSONString(map);
        response.getWriter().write(json);
    }
}
