package cn.wycclub.service;

import cn.wycclub.dao.po.OrderInfo;
import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.Order;

import java.util.List;

public interface BusinessOrdersService {

    //添加订单（基于购物车）
    Integer insertOrder(List<Integer> cids, int uid) throws Exception;

    //通过订单号查询订单
    OrderInfo selectOrderByKey(Integer id) throws Exception;

    //通过用户ID查询用户所有订单
    List<Order> selectOrderByUID(Integer id) throws Exception;

    //添加订单（基于现在购买）
    Integer insertOrder(Integer pid, Integer quantity, Integer uid) throws Exception;

    //付款
    boolean pay(Integer oid, UserInfo user) throws Exception;

    //修改订单状态
    boolean updateOrderState(int oid, int state);
}
