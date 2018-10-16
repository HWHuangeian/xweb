package com.huangweihan.xweb.service.impl;

import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/16 0016
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User login() {
        User user = new User();
        user.setId("001");
        user.setName("HWH");
        return user;
    }
}
