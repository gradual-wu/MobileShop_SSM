package cn.wycclub.dto;

import cn.wycclub.dao.po.ProductInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program:mobile
 * @Description:
 * @Author: WuYuchen
 * @Date: 2018-03-11 12:05
 */
public class Order {
    protected int oid;
    protected int state;
    protected BigDecimal payment;
    protected Map<ProductInfo, Integer> products = new HashMap<>();
    protected Date createTime;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Map<ProductInfo, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductInfo, Integer> products) {
        this.products = products;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
