package cn.wycclub.dao.po;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 分页查询
 *
 * @author WuYuchen
 * @create 2018-02-13 11:07
 **/

public class PageSelect implements Serializable {

    protected int offset;
    protected int limit;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
