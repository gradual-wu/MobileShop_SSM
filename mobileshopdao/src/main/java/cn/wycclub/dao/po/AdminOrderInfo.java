package cn.wycclub.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WuYuchen
 * @Description 管理员订单PO对象
 * @Date 2018-03-15 14:50
 */
public class AdminOrderInfo implements Serializable{
    protected int id;
    protected BigDecimal payment;
    protected int state;
    protected int uid;
    protected Date GmtCreate;
    protected Date GmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getGmtCreate() {
        return GmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        GmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return GmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        GmtModified = gmtModified;
    }
}
