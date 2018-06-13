package cn.wycclub.service;

import cn.wycclub.dao.mapper.*;
import cn.wycclub.dao.po.*;
import cn.wycclub.dto.*;
import cn.wycclub.exception.InsertProductException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WuYuchen
 * @Description 管理员业务逻辑层
 * @Date 2018-03-14 19:42
 */
@Service
public class BusinessAdminServiceImpl implements BusinessAdminService {
    @Autowired
    private ProductCartMapper productcartMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private UserCollectionMapper userCollectionMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    private Logger logger = Logger.getLogger(BusinessAdminServiceImpl.class);

    /**
     * 管理员主页数据汇总
     * */
    @Override
    public AdminIndex getSumByIndex() {
        logger.debug("获取商品总量");
        ProductInfoExample productExample = new ProductInfoExample();
        int productSum = productInfoMapper.countByExample(productExample);

        logger.debug("获取用户总量");
        UserInfoExample userExample = new UserInfoExample();
        int userSum = userInfoMapper.countByExample(userExample);

        logger.debug("获取订单总量");
        int orderSum = productOrderMapper.countSum();

        logger.debug("获取订单总交易额");
        BigDecimal paymentSum = productOrderMapper.selectSumByPayMent();

        logger.debug("生成显示数据");
        AdminIndex adminIndex = new AdminIndex();
        adminIndex.setOrderSum(orderSum);
        adminIndex.setPaymentSum(paymentSum);
        adminIndex.setProductSum(productSum);
        adminIndex.setUserSum(userSum);

        return adminIndex;
    }


    /**
     * 分页查询用户
     * */
    @Override
    public PageResult<UserInfo> getUser(PageInput pageInput) {
        UserInfoExample example = new UserInfoExample();

        logger.debug("创建分页查询条件");
        PageSelect pageSelect = new PageSelect();
        pageSelect.setOffset(pageInput.getStartIndex());
        pageSelect.setLimit(pageInput.getPageSize());
        example.setPageSelect(pageSelect);
        userInfoMapper.selectByExample(example);

        logger.debug("创建分页显示信息");
        PageResult<UserInfo> pageResult = new PageResult<>();
        pageResult.setList(userInfoMapper.selectByExample(example));
        pageResult.setCurrentPage(pageInput.getCurrentPage());
        pageResult.setPageSize(pageInput.getPageSize());
        pageResult.setTotalRecord(userInfoMapper.countByExample(example));

        return pageResult;
    }


    /**
     * 更新用户信息
     * */
    @Override
    public boolean updateUserInfo(int uid, int state) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(uid);
        userInfo.setState(state);
        int flag = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        logger.debug("更改了"+flag+"个用户状态");
        if (flag > 0) {
            return true;
        }
        return false;
    }

    /**
     * 分页查询订单信息
     * */
    @Override
    public PageResult<AdminOrder> getOrders(PageInput pageInput) {
        PageResult<AdminOrder> pageResult = new PageResult<>();
        List<AdminOrder> orders = new ArrayList<>();
        logger.debug("获取所有订单信息");

        logger.debug("整理需要查询的行数");
        PageSelect select = new PageSelect();
        select.setOffset(pageInput.getStartIndex());
        select.setLimit(pageInput.getPageSize());

        logger.debug("开始查询数据库");
        List<AdminOrderInfo> list = productOrderMapper.selectAllOrder(select);

        logger.debug("整理信息");
        for (AdminOrderInfo orderInfo : list) {
            AdminOrder order = new AdminOrder();
            order.setOid(orderInfo.getId());
            order.setState(orderInfo.getState());
            order.setCreateTime(orderInfo.getGmtCreate());
            order.setPayment(orderInfo.getPayment());
            order.setUsername(userInfoMapper.selectByPrimaryKey(orderInfo.getUid()).getUsername());
            for (ProductOrderConnInfo connInfo : productOrderMapper.selectAllConnInfo(orderInfo.getId())) {
                ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(connInfo.getPid());
                order.getProducts().put(productInfo, connInfo.getQuantity());
            }
            orders.add(order);
        }
        pageResult.setTotalRecord(productOrderMapper.countSum());
        pageResult.setPageSize(pageInput.getPageSize());
        pageResult.setList(orders);
        pageResult.setCurrentPage(pageInput.getCurrentPage());

        return pageResult;
    }


    @Override
    public PageResult<ProductInfo> getAllProduct(PageInput pageInput) {
        logger.debug("获取所有商品信息");
        PageResult<ProductInfo> pageResult = new PageResult<>();
        PageSelect select = new PageSelect();
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("pid desc");
        select.setLimit(pageInput.getPageSize());
        select.setOffset(pageInput.getStartIndex());
        example.setPageSelect(select);

        logger.debug("开始从数据库中查询商品信息");
        List<ProductInfo> list = productInfoMapper.selectByExample(example);

        logger.debug("整理商品数据");
        pageResult.setCurrentPage(pageInput.getCurrentPage());
        pageResult.setPageSize(pageInput.getPageSize());
        pageResult.setTotalRecord(productInfoMapper.countByExample(example));
        pageResult.setList(list);
        return pageResult;
    }

    @Transactional
    @Override
    public boolean addProductInfo(MultipartFile files[], ProductInfoInput productInfoInput, String path) throws IOException {
        logger.debug("准备插入商品信息");
        ProductInfo productInfo = new ProductInfo();
        productInfo.setStock(productInfoInput.getStock());
        productInfo.setBrand(productInfoInput.getBrand());
        productInfo.setGmtCreate(new Date());
        productInfo.setTitle(productInfoInput.getTitle());
        productInfo.setPrice(new BigDecimal(productInfoInput.getPrice()));

        logger.debug("开始插入商品数据");
        int flag = productInfoMapper.insertSelective(productInfo);
        if (flag <= 0) {
            throw new InsertProductException("插入商品信息出错");
        }


        logger.debug("整理商品图片");

        logger.debug("配置保存图片路径");
        String imageMainPath = path + File.separator + productInfo.getPid() + File.separator + "main";
        String imageDescPath = path + File.separator + productInfo.getPid() + File.separator + "desc";
        logger.debug("创建商品图片文件夹");
        File main = new File(imageMainPath);
        if (main.exists()) {
            main.mkdirs();
        }
        File desc = new File(imageDescPath);
        if (desc.exists()) {
            main.mkdirs();
        }
        logger.debug("开始写入图片文件");
        for (int i = 0 ; i < files.length ; i ++) {
            if (i < 4) {
                logger.debug("写入商品主图" + (i + 1));
                FileUtils.copyInputStreamToFile(files[i].getInputStream(), new File(imageMainPath, (i + 1) + ".jpg"));
            } else {
                logger.debug("写入商品介绍图" + (5 - i));
                FileUtils.copyInputStreamToFile(files[i].getInputStream(), new File(imageDescPath, (5 - i) + ".jpg"));
            }
        }

        return true;
    }
}
