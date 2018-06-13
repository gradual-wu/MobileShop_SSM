package cn.wycclub.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program:mobile
 * @Description:支付界面类
 * @Author: WuYuchen
 * @Date: 2018-03-09 9:49
 */
public class Pay {
    protected Integer oid;
    protected Map<String, Integer> products = new HashMap<>();
    protected BigDecimal payment;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "products=" + products +
                ", payment=" + payment +
                '}';
    }
}
