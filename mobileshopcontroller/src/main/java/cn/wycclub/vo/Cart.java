package cn.wycclub.vo;

import cn.wycclub.controller.ProductController;
import cn.wycclub.dao.po.ProductCart;
import cn.wycclub.dto.CartBean;

import java.util.List;
import java.util.Map;

/**
 * 购物车页面显示所需数据类
 *
 * @author WuYuchen
 * @create 2018-02-16 22:30
 **/

public class Cart {
    protected List<CartBean> list;

    public List<CartBean> getList() {
        return list;
    }

    public void setList(List<CartBean> list) {
        this.list = list;
    }
}
