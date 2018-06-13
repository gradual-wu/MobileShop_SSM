package cn.wycclub.service;

import cn.wycclub.dao.po.UserCollection;
import cn.wycclub.dto.Collection;

import java.util.List;

public interface BusinessCollectionService {
    //获取收藏列表
    List<Collection> getCollection(Integer id) throws Exception;
    //添加收藏
    boolean addCollection(Integer pid, Integer uid) throws Exception;
    //取消收藏
    boolean deleteCollection(Integer id) throws Exception;
    //判断指定商品是否收藏
    boolean getCollection(Integer uid, Integer pid) throws Exception;

    boolean deleteCollection(Integer pid, Integer uid) throws Exception;
}
