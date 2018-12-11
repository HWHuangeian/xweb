package com.huangweihan.xweb.service;

import com.huangweihan.xweb.entity.User;

import java.util.List;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/16 0016
 */
public interface UserService {

    /**
     * 根据用户名查询用户对象
     *
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 新增一个用户
     *
     * @param username
     * @return
     */
    Integer addOneUser(String username);

    /**
     * 删除一个用户
     *
     * @param username
     */
    void deleteByUserName(String username);

    /**
     * 删除所有用户
     */
    void deleteAllUser();

}
