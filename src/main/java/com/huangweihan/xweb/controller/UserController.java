package com.huangweihan.xweb.controller;

import com.huangweihan.xweb.core.pojo.ResultBean;
import com.huangweihan.xweb.entity.User;
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

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserByUserId")
    @ResponseBody
    public ResultBean<User> getUserByUserId(int userId) {
        User user = userService.getUserByUserId(userId);
        return new ResultBean<>(user);
    }

    @RequestMapping("/getUserByUserName")
    @ResponseBody
    public ResultBean<User> getUserByUserName(String userName) {
        User user = userService.getUserByUserName(userName);
        return new ResultBean<>(user);
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public ResultBean<List<User>> getAllUser() {
        List<User> userList = userService.getAllUser();
        return new ResultBean<>(userList);
    }

    @RequestMapping("/addOneUser")
    @ResponseBody
    public ResultBean<Integer> addOneUser(String userName) {
        logger.info("userName={}", userName);
        Integer userId = userService.addOneUser(userName);
        return new ResultBean<>(userId);
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
