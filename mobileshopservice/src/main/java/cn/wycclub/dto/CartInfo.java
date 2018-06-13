package cn.wycclub.dto;

/**
 * @author WuYuchen
 * @create 2018-02-15 0:02
 **/

public class CartInfo {

    protected int cid;
    protected int uid;
    protected int pid;
    protected int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "CartInfo{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", quantity=" + quantity +
                '}';
    }
}
