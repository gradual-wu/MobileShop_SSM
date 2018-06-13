package cn.wycclub.service;

import cn.wycclub.dao.mapper.UserInfoMapper;
import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dao.po.UserInfoExample;
import cn.wycclub.dto.RegisterForm;
import cn.wycclub.exception.UserExistException;
import cn.wycclub.util.ContextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 处理用户相关的业务逻辑类
 *
 * @author WuYuchen
 * @create 2018-02-13 22:00
 **/

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private Logger logger = Logger.getLogger(BusinessUserServiceImpl.class);

    @Override
    public boolean findLikeByUsername(String username) {
        logger.debug("进入BusinessUserServiceImpl的finLikeByUsername方法");
        logger.debug("创建查询对象UserInfoExample");
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        logger.debug("查找用户名" + username + "相同的用户");
        criteria.andUsernameEqualTo(username);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        logger.debug("获取到查询结果List<UserInfo> ::" + list);
        if (list.size() > 0) {
            logger.debug("找到用户名为:" + username + "的用户");
            return true;
        }
        logger.debug("未找到用户名为:" + username + "的用户");
        return false;
    }

    @Override
    public boolean register(RegisterForm registerForm) {
        logger.debug("进入service的注册流程");
        if (findLikeByUsername(registerForm.getUsername())) {
            logger.debug("二次检验用户名已经存在,无法注册");
            throw new UserExistException("用户名已存在!");
        }
        logger.debug("二次检验用户名不存在,进入正常注册流程");
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(registerForm.getEmail());
        userInfo.setPassword(ContextUtils.textToMd5(registerForm.getPassword()));
        userInfo.setUsername(registerForm.getUsername());
        userInfo.setGmtCreate(new Date());
        userInfoMapper.insertSelective(userInfo);
        return true;
    }

    @Override
    public UserInfo login(String username, String password) {
        logger.debug("进入service的登录流程");
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(ContextUtils.textToMd5(password));
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (list.size() > 0) {
            logger.debug("找到用户,返回用户对象给Controller");
            return list.get(0);
        }
        logger.debug("未找到用户,返回空值给Controller");
        return null;
    }

    @Override
    public List<UserInfo> autoLogin(String username) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        return  userInfoMapper.selectByExample(example);
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        if (userInfoMapper.updateByPrimaryKeySelective(userInfo) <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public UserInfo getUser(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
