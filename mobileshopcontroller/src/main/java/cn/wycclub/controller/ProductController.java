package cn.wycclub.controller;

import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.PageInputByProduct;
import cn.wycclub.dto.PageResult;
import cn.wycclub.service.BusinessCollectionService;
import cn.wycclub.service.BusinessProductService;
import cn.wycclub.vo.Category;
import cn.wycclub.vo.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 商品相关处理器
 *
 * @author WuYuchen
 * @create 2018-02-14 20:46
 **/

@Controller
public class ProductController {

    @Autowired
    private BusinessProductService service;

    @Autowired
    private BusinessCollectionService collectionService;

    private Logger logger = Logger.getLogger(ProductController.class);


    /**
     * 显示商品信息
     */
    @RequestMapping("/product.do")
    public ModelAndView viewProduct(int pid, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product product = new Product();
        product.setProductBean(service.getProductById(pid));
        product.setNewProduct(service.getProductByIndexPage(3));
        product.setRadomProduct(service.getProductByIndexPage(3));
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo != null) {
            logger.debug("用户已登陆，检测用户是否收藏该商品！");
            boolean flag = collectionService.getCollection(userInfo.getUid(), pid);
            logger.debug("用户是否收藏" + flag);
            product.setCollectionFlag(flag);
        }
        mv.addObject("product",product);
        mv.setViewName("/WEB-INF/jsp/product.jsp");
        return mv;
    }

    /**
     * 显示商品列表
     * */
    @RequestMapping("/category.do")
    public ModelAndView viewCategory(PageInputByProduct pageInputByProduct) throws Exception {
        ModelAndView mv = new ModelAndView();
        Category category = new Category();
        PageResult<ProductInfo> pageResult = service.getProductPage(pageInputByProduct);
        category.setBrand(pageInputByProduct.getBrand());
        category.setPageBean(pageResult);
        mv.addObject("category", category);
        mv.setViewName("/WEB-INF/jsp/category.jsp");
        return mv;
    }
}
