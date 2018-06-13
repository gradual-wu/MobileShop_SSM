package cn.wycclub.service;

import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dto.PageInputByProduct;
import cn.wycclub.dto.PageResult;

import java.util.List;

public interface BusinessProductService {
    //获取商品分页
    PageResult<ProductInfo> getProductPage(PageInputByProduct pageInputByProduct) throws Exception;
    //通过商品id获取商品
    ProductInfo getProductById(Integer id) throws Exception;
    //获取主页随机商品
    List<ProductInfo> getProductByIndexPage(Integer count) throws Exception;
    //获取所有商品品牌
    List<String> getAllBrand() throws Exception;
}
