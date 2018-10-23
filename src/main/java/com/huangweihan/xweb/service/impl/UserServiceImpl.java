package com.huangweihan.xweb.service.impl;

import com.huangweihan.xweb.dao.UserDao;
import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/16 0016
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUserId(int userId) {
        logger.info("userId={}", userId);
        User user = userDao.queryByUserId(userId);
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        logger.info("userName={}", userName);
        User user = userDao.queryByUserName(userName);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = userDao.queryAllUser();
        return userList;
    }

    @Override
    public Integer addOneUser(String userName) {
        logger.info("userName={}", userName);
        User instance = userDao.queryByUserName(userName);
        if (instance == null) {
            User user = new User();
            user.setUserName(userName);
            userDao.insertOneUser(user);
            return user.getUserId();
        } else {
            return -1;
        }
    }

    @Override
    public void deleteByUserId(int userId) {
        logger.info("userId={}", userId);
        userDao.deleteByUserId(userId);
    }

    @Override
    public void deleteAllUser() {
        userDao.deleteAllUser();
    }

    @Override
    public void updateOneUser(int userId, String userName) {
        logger.info("userId={}, userName={}", userId, userName);
        userDao.updateByUserId(userId, userName);
    }

}
