package com.huangweihan.xweb.service.impl;

import com.huangweihan.xweb.dao.UserDao;
import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
    public User getUserByUserName(String username) {
        logger.info("username={}", username);
        User user = userDao.queryByUserName(username);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = userDao.queryAllUser();
        return userList;
    }

    @Override
    public Integer addOneUser(String username) {
        logger.info("username={}", username);
        User instance = userDao.queryByUserName(username);
        if (instance == null) {
            User user = new User();
            user.setUserName(username);
            userDao.insertOneUser(user);
            return user.getId();
        } else {
            return -1;
        }
    }

    @Override
    public void deleteByUserName(String username) {
        logger.info("username={}", username);
        userDao.deleteByUserName(username);
    }

    @Override
    public void deleteAllUser() {
        userDao.deleteAllUser();
    }

}
