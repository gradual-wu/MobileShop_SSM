package cn.wycclub.dto;

import java.math.BigDecimal;

/**
 * @author WuYuchen
 * @Description 收藏
 * @Date 2018-03-14 18:54
 */
public class Collection {
    protected int cid;
    protected String title;
    protected BigDecimal price;
    protected int pid;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
