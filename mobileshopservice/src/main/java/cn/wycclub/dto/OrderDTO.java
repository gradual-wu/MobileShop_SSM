package cn.wycclub.dto;

/**
 * 封装用户传过来的商品id和商品数量
 *
 * @author WuYuchen
 * @create 2018-02-23 22:36
 **/

public class OrderDTO {

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
}
