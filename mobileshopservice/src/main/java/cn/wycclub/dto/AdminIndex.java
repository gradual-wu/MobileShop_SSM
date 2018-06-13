package cn.wycclub.dto;

import java.math.BigDecimal;

/**
 * @author WuYuchen
 * @Description 管理员主页信息
 * @Date 2018-03-14 19:55
 */
public class AdminIndex {
    protected int productSum;
    protected int userSum;
    protected int orderSum;
    protected BigDecimal paymentSum;

    public int getProductSum() {
        return productSum;
    }

    public void setProductSum(int productSum) {
        this.productSum = productSum;
    }

    public int getUserSum() {
        return userSum;
    }

    public void setUserSum(int userSum) {
        this.userSum = userSum;
    }

    public int getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(int orderSum) {
        this.orderSum = orderSum;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }
}
