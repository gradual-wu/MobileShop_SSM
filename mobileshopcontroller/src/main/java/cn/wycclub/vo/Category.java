package cn.wycclub.vo;

import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dto.PageResult;

/**
 * 商品分页列表
 *
 * @author WuYuchen
 * @create 2018-02-14 22:51
 **/

public class Category{

    protected PageResult<ProductInfo> pageBean;
    protected String brand;

    public PageResult<ProductInfo> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageResult<ProductInfo> pageBean) {
        this.pageBean = pageBean;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
