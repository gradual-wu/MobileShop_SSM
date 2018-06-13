package cn.wycclub.dto;


import cn.wycclub.dao.po.ProductCart;
import cn.wycclub.dao.po.ProductInfo;


/**
 * @author WuYuchen
 * @create 2018-02-16 22:43
 **/

public class CartBean extends ProductCart {

    protected ProductInfo productInfo;

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

}
