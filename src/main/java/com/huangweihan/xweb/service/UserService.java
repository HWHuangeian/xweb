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
     * 查询所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 新增一个用户
     *
     * @param userName
     * @return
     */
    Integer addOneUser(String userName);

    /**
     *  删除一个用户
     *
     * @param userId
     */
    void deleteByUserId(int userId);

    /**
     * 删除所有用户
     */
    void deleteAllUser();

    /**
     * 更新一个用户
     *
     * @param userId
     * @param userName
     */
    void updateOneUser(int userId, String userName);
}
