package cn.wycclub.service;

import cn.wycclub.dto.CartBean;
import cn.wycclub.dto.CartInfo;

import java.util.List;

/**
 * 购物车业务逻辑层接口
 *
 * @author WuYuchen
 * @create 2018-02-14 23:59
 **/

public interface BusinessCartService {

    boolean insertCart(CartInfo cartInfo) throws Exception;

    boolean deleteCart(Integer cid) throws Exception;

    CartBean updateCart(CartInfo cartInfo) throws Exception;

    List<CartBean> selectCart(Integer uid) throws Exception;
}
