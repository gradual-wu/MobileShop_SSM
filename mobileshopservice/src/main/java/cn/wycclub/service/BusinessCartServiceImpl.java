package cn.wycclub.service;

import cn.wycclub.dao.mapper.ProductCartMapper;
import cn.wycclub.dao.mapper.ProductInfoMapper;
import cn.wycclub.dao.po.ProductCart;
import cn.wycclub.dao.po.ProductCartExample;
import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dto.CartBean;
import cn.wycclub.dto.CartInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 购物车业务逻辑层
 *
 * @author WuYuchen
 * @create 2018-02-15 0:48
 **/
@Service
public class BusinessCartServiceImpl implements BusinessCartService {

    @Autowired
    private ProductCartMapper productCartMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    private Logger log = Logger.getLogger(BusinessCartServiceImpl.class);

    @Override
    public boolean insertCart(CartInfo cartInfo) {
        log.debug("cart业务逻辑层(insertCart)");

        log.debug("检查数据库中是该用户是否存在相同商品");
        ProductCartExample example = new ProductCartExample();
        ProductCartExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(cartInfo.getUid());
        criteria.andPidEqualTo(cartInfo.getPid());
        List<ProductCart> cart = productCartMapper.selectByExample(example);
        ProductCart productCart;
        BigDecimal price = productInfoMapper.selectByPrimaryKey(cartInfo.getPid()).getPrice();
        int flag;
        if (cart.size() > 0) {
            log.debug("数据库中该用户购物车中存在相同商品，则修改商品数量");
            productCart = cart.get(0);
            int quantity = productCart.getQuantity() + cartInfo.getQuantity();
            productCart.setQuantity(quantity);
            productCart.setPayable(price.multiply(new BigDecimal(quantity)));
            flag = productCartMapper.updateByPrimaryKeySelective(productCart);
        } else {
            log.debug("数据库中该用户购物车中不存在相同商品，给数据库新增一条记录");
            productCart = new ProductCart();
            productCart.setPid(cartInfo.getPid());
            productCart.setUid(cartInfo.getUid());
            productCart.setGmtCreate(new Date());
            productCart.setQuantity(cartInfo.getQuantity());
            productCart.setPayable(price.multiply(new BigDecimal(cartInfo.getQuantity())));
            flag = productCartMapper.insertSelective(productCart);
        }
        if (flag > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCart(Integer cid) {
        log.debug("删除购物车中cid为"+cid+"的项");
        int flag = productCartMapper.deleteByPrimaryKey(cid);
        if (flag > 0) {
            return true;
        }
        return false;
    }

    @Override
    public CartBean updateCart(CartInfo cartInfo) throws InvocationTargetException, IllegalAccessException {
        log.debug("更新商品信息");
        ProductCart cart = new ProductCart();
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(cartInfo.getPid());
        BigDecimal price = productInfo.getPrice();
        cart.setPayable(price.multiply(new BigDecimal(cartInfo.getQuantity())));
        cart.setQuantity(cartInfo.getQuantity());
        cart.setUid(cartInfo.getUid());
        cart.setCid(cartInfo.getCid());
        cart.setPid(cartInfo.getPid());
        CartBean cartBean = new CartBean();
        BeanUtils.copyProperties(cartBean, cart);
        cartBean.setProductInfo(productInfo);

        int flag = productCartMapper.updateByPrimaryKeySelective(cart);
        if (flag > 0) {
            return cartBean;
        }
        return null;
    }

    @Override
    public List<CartBean> selectCart(Integer uid) throws InvocationTargetException, IllegalAccessException {
        ProductCartExample example = new ProductCartExample();
        ProductCartExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<ProductCart> list = productCartMapper.selectByExample(example);
        List<CartBean> beans = new LinkedList<>();
        for (ProductCart cart : list) {
            CartBean cartBean = new CartBean();
            BeanUtils.copyProperties(cartBean, cart);
            cartBean.setProductInfo(productInfoMapper.selectByPrimaryKey(cart.getPid()));
            beans.add(cartBean);
        }
        return beans;
    }
}
