package cn.wycclub.service;

import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author WuYuchen
 * @Description
 * @Date 2018-03-14 19:59
 */
public interface BusinessAdminService {
    //管理员主页数据汇总
    AdminIndex getSumByIndex() throws Exception;

    //用户信息分页查询
    PageResult<UserInfo> getUser(PageInput pageInput) throws Exception;

    //更新用户信息
    boolean updateUserInfo(int uid, int state) throws Exception;

    //订单信息分页查询
    PageResult<AdminOrder> getOrders(PageInput pageInput) throws Exception;

    //商品信息分页查询
    PageResult<ProductInfo> getAllProduct(PageInput pageInput) throws Exception;

    //添加商品
    boolean addProductInfo(MultipartFile files[], ProductInfoInput productInfoInput, String path) throws Exception;
}
