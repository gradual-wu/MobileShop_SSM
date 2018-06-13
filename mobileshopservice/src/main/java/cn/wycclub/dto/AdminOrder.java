package cn.wycclub.dto;

/**
 * @author WuYuchen
 * @Description 管理员订单信息
 * @Date 2018-03-15 14:19
 */
public class AdminOrder extends Order {
    protected String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
