package cn.wycclub.service;

import cn.wycclub.dao.mapper.ProductInfoMapper;
import cn.wycclub.dao.mapper.UserCollectionMapper;
import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dao.po.UserCollection;
import cn.wycclub.dao.po.UserCollectionExample;
import cn.wycclub.dto.Collection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 收藏相关业务逻辑层
 *
 * @author WuYuchen
 * @create 2018-02-16 22:12
 **/

@Service
public class BusinessCollectionServiceImpl implements BusinessCollectionService {

    @Autowired
    private UserCollectionMapper userCollectionMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    private Logger log = Logger.getLogger(BusinessCollectionServiceImpl.class);

    @Override
    public List<Collection> getCollection(Integer id) {
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        log.debug("获取用户ID为:" + id + "的商品收藏列表");
        criteria.andUidEqualTo(id);
        List<UserCollection> list = userCollectionMapper.selectByExample(example);
        List<Collection> collections = new LinkedList<>();
        for (UserCollection userCollection : list) {
            Collection collection = new Collection();
            collection.setCid(userCollection.getCid());
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(userCollection.getPid());
            collection.setPrice(productInfo.getPrice());
            collection.setTitle(productInfo.getTitle());
            collection.setPid(productInfo.getPid());
            collections.add(collection);
        }
        return collections;
    }

    @Override
    public boolean addCollection(Integer pid, Integer uid) {
        log.debug("为用户id为:" + uid + "的用户添加收藏");
        UserCollection userCollection = new UserCollection();
        userCollection.setPid(pid);
        userCollection.setUid(uid);
        userCollection.setGmtCreate(new Date());
        int i = userCollectionMapper.insertSelective(userCollection);
        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean getCollection(Integer uid, Integer pid) {
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andPidEqualTo(pid);
        List list = userCollectionMapper.selectByExample(example);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCollection(Integer id) {
        log.debug("删除收藏ID为:" + id + "的收藏信息");
        int flag = userCollectionMapper.deleteByPrimaryKey(id);
        if (flag > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCollection(Integer pid, Integer uid) {
        log.debug("取消收藏");
        UserCollectionExample example = new UserCollectionExample();
        UserCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andPidEqualTo(pid);
        int flag = userCollectionMapper.deleteByExample(example);
        if (flag > 0) {
            return true;
        }
        return false;
    }
}
