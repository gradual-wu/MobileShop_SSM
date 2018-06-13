package cn.wycclub.dao.po;


import java.io.Serializable;
import java.util.Date;

/**
 * 订单和商品关联信息
 *
 * @author WuYuchen
 * @create 2018-02-19 19:03
 **/

public class ProductOrderConnInfo implements Serializable {
    protected int oid;
    protected int pid;
    protected int quantity;
    protected Date gmtCreate = new Date();
    protected Date gmtModified;

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

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductOrderConnInfo{" +
                "oid=" + oid +
                ", pid=" + pid +
                ", quantity=" + quantity +
                ", gmtCreate=" + gmtCreate.toLocaleString() +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
