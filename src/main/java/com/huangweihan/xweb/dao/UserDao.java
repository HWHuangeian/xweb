package com.huangweihan.xweb.dao;

import com.huangweihan.xweb.entity.User;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/17 0017
 */
public interface UserDao {

    /**
     * 根据用户ID查询用户对象
     *
     * @param userId
     * @return
     */
    User queryByUserId(int userId);

    /**
     * 根据用户名查询用户对象
     *
     * @param userName
     * @return
     */
    User queryByUserName(String userName);
}
