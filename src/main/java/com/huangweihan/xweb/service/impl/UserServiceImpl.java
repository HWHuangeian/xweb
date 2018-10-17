package com.huangweihan.xweb.service.impl;

import com.huangweihan.xweb.core.constant.CommonConstant;
import com.huangweihan.xweb.dao.UserDao;
import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/16 0016
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUserId(int userId) {
        User user = userDao.queryByUserId(userId);
        logger.info("userId={}, userName={}", user.getUserId(), user.getUserName());
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userDao.queryByUserName(userName);
        logger.info("userId={}, userName={}", user.getUserId(), user.getUserName());
        return user;
    }

    @Override
    public void addOneUser(String userName) {
        logger.info("userName={}", userName);
        userDao.insertOneUser(userName);
    }
}
