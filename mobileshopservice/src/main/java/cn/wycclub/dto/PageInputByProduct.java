package cn.wycclub.dto;

/**
 * 商品初始分页数据
 *
 * @author WuYuchen
 * @create 2018-02-13 21:23
 **/

public class PageInputByProduct extends PageInput {

    protected String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
