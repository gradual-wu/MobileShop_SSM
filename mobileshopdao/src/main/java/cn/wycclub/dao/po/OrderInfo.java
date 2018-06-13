package cn.wycclub.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 订单PO
 *
 * @author WuYuchen
 * @create 2018-02-19 18:59
 **/

public class OrderInfo implements Serializable {
    protected Integer id;
    protected Integer uid;
    protected List<ProductOrderConnInfo> list = new LinkedList<>();
    protected BigDecimal payment;
    protected Integer state;
    protected Date gmtCreate = new Date();
    protected Date gmtModified;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProductOrderConnInfo> getList() {
        return list;
    }

    public void setList(List<ProductOrderConnInfo> list) {
        this.list = list;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", list=" + list +
                ", payment=" + payment +
                ", state=" + state +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
