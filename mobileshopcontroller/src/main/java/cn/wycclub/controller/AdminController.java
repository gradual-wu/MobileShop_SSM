package cn.wycclub.controller;

import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.*;
import cn.wycclub.interceptor.AdminLogin;
import cn.wycclub.service.BusinessAdminService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuYuchen
 * @Description 管理员处理器
 * @Date 2018-03-14 19:39
 */
@Controller
public class AdminController implements AdminLogin {

    @Autowired
    protected BusinessAdminService adminService;

    protected Logger logger = Logger.getLogger(AdminController.class);

    /**
     * 管理员主页
     * */
    @RequestMapping("/index.admin")
    public ModelAndView toIndex() throws Exception {
        ModelAndView mv = new ModelAndView();
        AdminIndex adminIndex = adminService.getSumByIndex();
        mv.addObject("admin", adminIndex);
        mv.setViewName("/WEB-INF/jsp/admin/index.jsp");
        return mv;
    }

    /**
     * 用户管理
     * */
    @RequestMapping("/userManager.admin")
    public ModelAndView toUserManager(PageInput pageInput) throws Exception {
        ModelAndView mv = new ModelAndView();
        PageResult<UserInfo> pageResult = adminService.getUser(pageInput);
        mv.addObject("pageResult", pageResult);
        mv.setViewName("/WEB-INF/jsp/admin/userManager.jsp");
        return mv;
    }


    /**
     * 更新用户信息
     * */
    @RequestMapping("/updateUserInfo.admin")
    public void updateUser(int uid, int state, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (adminService.updateUserInfo(uid, state)) {
            map.put("message", "用户状态修改成功");
            map.put("flag", "true");
        } else {
            map.put("message", "用户状态修改失败");
            map.put("flag", "false");
        }
        String json = JSONObject.toJSONString(map);
        response.getWriter().write(json);
    }

    /**
     * 订单管理
     * */
    @RequestMapping("/ordersManager.admin")
    public ModelAndView toOrderManager(PageInput pageInput) throws Exception {
        ModelAndView mv = new ModelAndView();
        PageResult<AdminOrder> list = adminService.getOrders(pageInput);
        mv.addObject("pageResult", list);
        mv.setViewName("/WEB-INF/jsp/admin/ordersManager.jsp");
        return mv;
    }

    /**
     * 商品管理
     * */
    @RequestMapping("/productManager.admin")
    public ModelAndView toProductManager(PageInput pageInput) throws Exception {
        logger.debug("传入分页信息：" + pageInput);
        ModelAndView mv = new ModelAndView();
        logger.debug("获得商品分页");
        PageResult<ProductInfo> list = adminService.getAllProduct(pageInput);
        logger.debug("将查询到的数据返回到页面");
        mv.addObject("pageResult", list);
        mv.setViewName("/WEB-INF/jsp/admin/productManager.jsp");
        return mv;
    }

    /**
     * 添加商品
     * */
    @RequestMapping("/uploadProduct.admin")
    public String uploadProduct(@RequestParam("files")MultipartFile[] files, ProductInfoInput productInfoInput, HttpServletRequest request) {
        logger.debug("插入商品信息");
        String path = request.getServletContext().getRealPath("/images/product");
        try {
            adminService.addProductInfo(files, productInfoInput, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:productManager.admin";
    }
}
