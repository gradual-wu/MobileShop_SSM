package cn.wycclub.controller;

import cn.wycclub.dao.po.OrderInfo;
import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dao.po.ProductOrderConnInfo;
import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.Order;
import cn.wycclub.dto.OrderDTO;
import cn.wycclub.dto.ProductInfoByOrder;
import cn.wycclub.interceptor.UserLogin;
import cn.wycclub.service.BusinessOrdersService;
import cn.wycclub.service.BusinessProductService;
import cn.wycclub.service.BusinessUserService;
import cn.wycclub.vo.Pay;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单处理器
 *
 * @author WuYuchen
 * @create 2018-02-23 23:00
 **/

@Controller
public class OrderController implements UserLogin {

    @Autowired
    private BusinessOrdersService ordersService;

    @Autowired
    private BusinessProductService productService;

    @Autowired
    private BusinessUserService userService;

    private Logger logger = Logger.getLogger(OrderController.class);


    /**
     * 创建订单
     * */
    @RequestMapping("/createOrders.do")
    @ResponseBody
    public Object createOrders(HttpSession session, @RequestBody String cids) throws IOException {
        Map map = new HashMap();
        try {
            logger.debug("接收到客户端提交的订单商品信息");
            JSONObject jsonObject = new JSONObject();
            List<Integer> ids = jsonObject.parseArray(cids, Integer.class);
            UserInfo user = (UserInfo) session.getAttribute("user");
            Integer oid = ordersService.insertOrder(ids, user.getUid());
            String url = session.getServletContext().getContextPath() + "/toShowPayView.do?oid="+oid;
            map.put("message", "创建订单成功，正在为您跳转到支付界面！");
            map.put("flag", "true");
            map.put("url", url);
        } catch (Exception e) {
            map.put("message", "创建订单失败，请稍后再试！");
            map.put("flag", "false");
            e.printStackTrace();
        } finally {
            return map;
        }
    }


    /**
     * 显示付款界面
     * */
    @RequestMapping("/toShowPayView.do")
    public void toShowPayView(int oid, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("进入订单支付显示");
        ModelAndView mv = new ModelAndView();
        try {
            logger.debug("显示订单支付界面！订单id：" + oid);
            OrderInfo orderInfo = ordersService.selectOrderByKey(oid);
            if (orderInfo.getState() != 0) {
                request.setAttribute("message", "该订单已操作，无法二次付款！");
                request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
                return;
            }
            Pay pay = new Pay();
            for (ProductOrderConnInfo connInfo : orderInfo.getList()) {
                ProductInfo productInfo = productService.getProductById(connInfo.getPid());
                pay.getProducts().put(productInfo.getTitle(), connInfo.getQuantity());
            }
            pay.setOid(oid);
            pay.setPayment(orderInfo.getPayment());
            request.setAttribute("pay", pay);
            request.getRequestDispatcher("/WEB-INF/jsp/pay.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 用户支付
     * */
    @RequestMapping("/pay.do")
    public void pay(UserInfo userInfo, Integer oid, HttpSession session, HttpServletResponse response) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            UserInfo serviceUserInfo = (UserInfo) session.getAttribute("user");
            int uid = serviceUserInfo.getUid();
            logger.debug("查询订单id为：" + oid + "的订单");
            userInfo.setUid(uid);
            if (ordersService.pay(oid, userInfo)) {
                logger.debug("付款成功，进行跳转");
                map.put("message", "付款成功，正在为您跳转到您的订单界面！");
                map.put("flag", "true");
            } else {
                map.put("message", "付款失败，请稍后再试！");
                map.put("flag", "false");
            }
            session.setAttribute("user", userService.getUser(uid));
        } catch (Exception e) {
            map.put("message", "付款失败，请稍后再试！");
            map.put("flag", "false");
        } finally {
            map.put("url", "toShowOrdersList.do");
            String json = JSONObject.toJSONString(map);
            try {
                logger.debug("发送给前端页面的JSON：：" + json);
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 显示所有订单
     * */
    @RequestMapping("/toShowOrdersList.do")
    public void toShowOrdersList(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            UserInfo userInfo = (UserInfo) session.getAttribute("user");
            List<Order> list = ordersService.selectOrderByUID(userInfo.getUid());
            request.setAttribute("orders", list);
            request.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 更新订单状态
     * */
    @RequestMapping(value = "/updateState.do", method = RequestMethod.POST)
    public void updateStateByOrder(int state, int oid, HttpServletResponse response) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            if (ordersService.updateOrderState(oid, state)) {
                map.put("message", "收货成功！");
                map.put("flag", "true");
            } else {
                map.put("message", "订单异常！");
                map.put("flag", "false");
            }
        } catch (Exception e) {
            map.put("message", "订单异常！");
            map.put("flag", "false");
        } finally {
            String json = JSONObject.toJSONString(map);
            try {
                logger.debug("回写JSON数据" + json);
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 商品立即购买
     * */
    @RequestMapping(value = "/nowPay.do", method = RequestMethod.POST)
    public void nowPay(HttpServletResponse response, HttpSession session, Integer pid, Integer quantity) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            logger.debug("现在付款流程");
            int oid = ordersService.insertOrder(pid, quantity, userInfo.getUid());
            logger.debug("生成订单，订单ID：" + oid);
            map.put("flag", "true");
            map.put("message", "正在跳转到付款界面！");
            map.put("url", "toShowPayView.do?oid=" + oid);
        } catch (Exception e) {
            map.put("flag", "false");
            map.put("message", "服务器出错，无法开始交易！");
            map.put("url", "index.do");
            e.printStackTrace();
        } finally {
            String json = JSONObject.toJSONString(map);
            try {
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
