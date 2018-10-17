package com.huangweihan.xweb.dao;

import com.huangweihan.xweb.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> queryAllUser();

    /**
     * 新增一个用户
     *
     * @param user
     * @return
     */
    void insertOneUser(User user);

    /**
     * 根据用户ID删除一个用户
     *
     * @param userId
     */
    void deleteByUserId(int userId);

    /**
     * 删除所有用户
     */
    void deleteAllUser();

    /**
     * 更新一个用户的用户信息
     *
     * @param userId
     * @param userName
     */
    void updateByUserId(@Param("userId")int userId, @Param("userName")String userName);
}