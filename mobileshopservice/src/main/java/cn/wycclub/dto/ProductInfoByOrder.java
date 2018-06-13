package cn.wycclub.dto;

public class ProductInfoByOrder {

    protected Integer pid;
    protected Integer quantity;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductInfoByOrder{" +
                "pid=" + pid +
                ", quantity=" + quantity +
                '}';
    }
}
