package cn.wycclub.view;

import cn.wycclub.dao.mapper.ProductInfoMapper;
import cn.wycclub.service.BusinessProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 主页显示
 *
 * @author WuYuchen
 * @create 2018-02-14 0:20
 **/

@Controller
public class IndexView {

    @Autowired
    private BusinessProductService service;

    @RequestMapping("/index.do")
    public ModelAndView toIndex(HttpSession session) throws Exception {
        session.setAttribute("brandList", service.getAllBrand());
        ModelAndView mv = new ModelAndView();
        mv.addObject("radomProductListGroom", service.getProductByIndexPage(4));
        mv.addObject("radomProductListLike", service.getProductByIndexPage(4));
        mv.setViewName("/WEB-INF/jsp/index.jsp");
        return mv;
    }

}
