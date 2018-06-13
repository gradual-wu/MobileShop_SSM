package cn.wycclub.dao.mapper;

import cn.wycclub.dao.po.AdminOrderInfo;
import cn.wycclub.dao.po.OrderInfo;
import cn.wycclub.dao.po.PageSelect;
import cn.wycclub.dao.po.ProductOrderConnInfo;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Repository("productOrderMapper")
public interface ProductOrderMapper {

    //添加订单
    int insertOrder(OrderInfo orderInfo);

    //添加订单商品
    int insertBatchByConn(ProductOrderConnInfo conn);

    //通过订单号查询订单
    OrderInfo selectOrderByKey(Integer id);

    //通过用户ID查询订单
    List<OrderInfo> selectOrderByUID(Integer id);

    //获取订单状态
    int selectOrderState(Integer oid);

    //修改订单状态
    int updateOrderState(OrderInfo orderInfo);

    //获取订单总量
    int countSum();

    //获取总订单金额
    BigDecimal selectSumByPayMent();

    //获取订单分页
    List<AdminOrderInfo> selectAllOrder(PageSelect pageSelect);

    //获取订单商品关联表信息
    List<ProductOrderConnInfo> selectAllConnInfo(Integer oid);

}
