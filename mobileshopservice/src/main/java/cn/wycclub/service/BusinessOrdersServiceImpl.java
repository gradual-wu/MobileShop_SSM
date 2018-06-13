package cn.wycclub.service;

import cn.wycclub.dao.mapper.ProductCartMapper;
import cn.wycclub.dao.mapper.ProductInfoMapper;
import cn.wycclub.dao.mapper.ProductOrderMapper;
import cn.wycclub.dao.mapper.UserInfoMapper;
import cn.wycclub.dao.po.*;
import cn.wycclub.dto.Order;
import cn.wycclub.exception.OrderCreateException;
import cn.wycclub.exception.PayException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 订单相关业务逻辑处理
 *
 * @author WuYuchen
 * @create 2018-02-19 14:36
 **/
@Service
public class BusinessOrdersServiceImpl implements BusinessOrdersService {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductCartMapper productCartMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    private Logger logger = Logger.getLogger(BusinessOrdersServiceImpl.class);

    /**
     * 创建订单（基于购物车）
     * */
    @Transactional
    @Override
    public Integer insertOrder(List<Integer> cids, int uid) {
        try {
            logger.debug("创建OrderInfo对象");
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setGmtCreate(new Date());
            orderInfo.setUid(uid);
            BigDecimal payment = new BigDecimal(0);

            logger.debug("获取订单总价钱");
            for (Integer cid : cids) {
                ProductOrderConnInfo connInfo = new ProductOrderConnInfo();
                ProductCart productCart = productCartMapper.selectByPrimaryKey(cid);
                connInfo.setPid(productCart.getPid());
                connInfo.setQuantity(productCart.getQuantity());
                orderInfo.getList().add(connInfo);
                payment = payment.add(productCart.getPayable());
            }
            orderInfo.setPayment(payment);

            logger.debug("生成订单");
            orderInfo.setPayment(payment);
            productOrderMapper.insertOrder(orderInfo);

            logger.debug("加入订单商品");
            for (ProductOrderConnInfo conn : orderInfo.getList()) {
                conn.setOid(orderInfo.getId());
                productOrderMapper.insertBatchByConn(conn);
            }

            logger.debug("删除购物车中的项目");
            for (int cid : cids) {
                productCartMapper.deleteByPrimaryKey(cid);
            }

            return orderInfo.getId();
        } catch (Exception e) {
            throw new OrderCreateException(e);
        }
    }

    /**
     * 添加订单（基于现在购买）
     * */
    @Transactional
    @Override
    public Integer insertOrder(Integer pid, Integer quantity, Integer uid) {
        logger.debug("创建订单");

        ProductOrderConnInfo connInfo = new ProductOrderConnInfo();
        connInfo.setPid(pid);
        connInfo.setQuantity(quantity);
        connInfo.setGmtCreate(new Date());

        logger.debug("创建订单对象");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUid(uid);
        orderInfo.setGmtCreate(new Date());

        logger.debug("查询加入订单的商品的价钱");
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(pid);
        BigDecimal price = productInfo.getPrice();
        orderInfo.setPayment(price.multiply(new BigDecimal(quantity)));

        logger.debug("将订单提交到数据库");
        int flag = productOrderMapper.insertOrder(orderInfo);
        if (flag <= 0) {
            throw new OrderCreateException("订单创建失败");
        }

        logger.debug("订单关联信息提交到数据库中");
        connInfo.setOid(orderInfo.getId());
        flag = productOrderMapper.insertBatchByConn(connInfo);
        if (flag <= 0) {
            throw new OrderCreateException("订单创建失败");
        }
        return orderInfo.getId();
    }

    /**
     * 用户订单支付
     * */
    @Transactional
    @Override
    public boolean pay(Integer oid, UserInfo user) {
        int uid = user.getUid();
        logger.debug("传入用户ID:" + uid + "，订单ID：" + oid);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uid);
        OrderInfo orderInfo = productOrderMapper.selectOrderByKey(oid);
        logger.debug("判断用户余额是否足以支付订单款项");
        if (userInfo.getMoney().compareTo(orderInfo.getPayment()) < 0) {
            throw new PayException("用户余额不足！");
        }

        logger.debug("进行扣款行为");

        logger.debug("用户余额：" + userInfo.getMoney());
        logger.debug("扣款金额：" + orderInfo.getPayment());
        userInfo.setMoney(userInfo.getMoney().subtract(orderInfo.getPayment()));
        logger.debug("修改用户收货地址:"+user.getAddress()+"和手机号:"+user.getTel());
        user.setMoney(userInfo.getMoney());
        logger.debug("开始扣款");
        int flag = userInfoMapper.updateByPrimaryKeySelective(user);
        if (flag <= 0) {
            logger.debug("扣款失败！");
            throw new PayException("扣款失败");
        }

        logger.debug("进行订单付款核对行为");
        orderInfo.setState(1);
        flag = productOrderMapper.updateOrderState(orderInfo);
        if (flag <= 0) {
            throw new PayException("更改订单状态失败！");
        }

        logger.debug("商品库存修改");
        for (ProductOrderConnInfo connInfo : orderInfo.getList()) {
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(connInfo.getPid());
            ProductInfo productInfo1 = new ProductInfo();
            productInfo1.setStock(productInfo.getStock() - connInfo.getQuantity());
            productInfo1.setPid(connInfo.getPid());
            productInfoMapper.updateByPrimaryKeySelective(productInfo1);
        }

        return true;
    }

    /**
     * 修改订单状态
     * */
    public boolean updateOrderState(int oid, int state) {
        OrderInfo orderInfo = selectOrderByKey(oid);
        if (state == 2 && orderInfo.getState() == 1) {
            orderInfo.setState(state);
            int flag = productOrderMapper.updateOrderState(orderInfo);
            if (flag > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取订单（通过订单id）
     * */
    @Override
    public OrderInfo selectOrderByKey(Integer id) {
        return productOrderMapper.selectOrderByKey(id);
    }

    /**
     * 获取订单（通过用户id）
     * */
    @Override
    public List<Order> selectOrderByUID(Integer id) {
        logger.debug("查询用户id为"+ id + "的所有订单信息");
        List<OrderInfo> list = productOrderMapper.selectOrderByUID(id);
        List<Order> orders = new LinkedList<>();

        for (OrderInfo orderInfo : list) {
            Order order = new Order();
            order.setPayment(orderInfo.getPayment());
            order.setOid(orderInfo.getId());
            order.setState(orderInfo.getState());
            order.setCreateTime(orderInfo.getGmtCreate());
            for (ProductOrderConnInfo connInfo : orderInfo.getList()) {
                ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(connInfo.getPid());
                order.getProducts().put(productInfo, connInfo.getQuantity());
            }
            orders.add(order);
        }

        return orders;
    }

}
