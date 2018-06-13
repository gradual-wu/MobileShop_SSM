package cn.wycclub.dto;

import java.math.BigDecimal;

/**
 * @author WuYuchen
 * @Description 商品信息上传数据
 * @Date 2018-03-15 19:30
 */
public class ProductInfoInput {
    protected String title;
    protected String brand;
    protected String price;
    protected int stock;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
