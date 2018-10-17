package com.huangweihan.xweb.service;

import com.huangweihan.xweb.entity.User;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/16 0016
 */
public interface UserService {

    /**
     * 根据用户ID查询用户对象
     *
     * @param userId
     * @return
     */
    User getUserByUserId(int userId);

    /**
     * 根据用户名查询用户对象
     *
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 新增一个用户
     *
     * @param userName
     */
    void addOneUser(String userName);
}
