package com.huangweihan.xweb.controller;

import com.huangweihan.xweb.core.pojo.ResultBean;
import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.CacheService;
import com.huangweihan.xweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description
 *
 * @author Administrator
 * @date 2018-10-16
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CacheService cacheService;

    @RequestMapping("/setCache")
    @ResponseBody
    public ResultBean<Boolean> setCache(String key, String value) {
        User user = new User();
        user.setUserId(12);
        user.setUserName("HWH");
        cacheService.setCache(key, user);
        return new ResultBean<>(true);
    }

    @RequestMapping("/getCache")
    @ResponseBody
    public ResultBean<User> getCache(String key) {
        User user = cacheService.getCache(key);
        return new ResultBean<>(user);
    }

    @RequestMapping("/getUserByUserId")
    @ResponseBody
    public ResultBean<User> getUserByUserId(int userId) {
        return new ResultBean<>(userService.getUserByUserId(userId));
    }

    @RequestMapping("/getUserByUserName")
    @ResponseBody
    public ResultBean<User> getUserByUserName(String userName) {
        return new ResultBean<>(userService.getUserByUserName(userName));
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public ResultBean<List<User>> getAllUser() {
        return new ResultBean<>(userService.getAllUser());
    }

    @RequestMapping("/addOneUser")
    @ResponseBody
    public ResultBean<Integer> addOneUser(String userName) {
        logger.info("userName={}", userName);
        return new ResultBean<>(userService.addOneUser(userName));
    }

    @RequestMapping("/deleteOneUser")
    @ResponseBody
    public ResultBean<Boolean> deleteOneUser(int userId) {
        logger.info("userId={}", userId);
        userService.deleteByUserId(userId);
        return new ResultBean<>(true);
    }

    @RequestMapping("/deleteAllUser")
    @ResponseBody
    public ResultBean<Boolean> deleteAllUser() {
        userService.deleteAllUser();
        return new ResultBean<>(true);
    }

    @RequestMapping("/updateOneUser")
    @ResponseBody
    public ResultBean<Boolean> updateOneUser(int userId, String userName) {
        logger.info("userId={}, userName={}", userId, userName);
        userService.updateOneUser(userId, userName);
        return new ResultBean<>(true);
    }

}
