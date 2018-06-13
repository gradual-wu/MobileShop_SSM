package cn.wycclub.vo;

import cn.wycclub.dao.po.ProductInfo;

import java.util.List;
import java.util.Map;

/**
 * 商品显示需要信息
 *
 * @author WuYuchen
 * @create 2018-02-14 23:00
 **/

public class Product {
    protected ProductInfo productBean;
    protected List<ProductInfo> radomProduct;
    protected List<ProductInfo> newProduct;
    protected boolean collectionFlag;

    public boolean isCollectionFlag() {
        return collectionFlag;
    }

    public void setCollectionFlag(boolean collectionFlag) {
        this.collectionFlag = collectionFlag;
    }

    public ProductInfo getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductInfo productBean) {
        this.productBean = productBean;
    }

    public List<ProductInfo> getRadomProduct() {
        return radomProduct;
    }

    public void setRadomProduct(List<ProductInfo> radomProduct) {
        this.radomProduct = radomProduct;
    }

    public List<ProductInfo> getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(List<ProductInfo> newProduct) {
        this.newProduct = newProduct;
    }
}
