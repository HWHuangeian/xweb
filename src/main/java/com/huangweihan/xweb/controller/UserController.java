package com.huangweihan.xweb.controller;

import com.huangweihan.xweb.core.constant.CommonConstant;
import com.huangweihan.xweb.core.pojo.Result;
import com.huangweihan.xweb.core.utils.ResultUtil;
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
    public Result<User> getUserByUserId(int userId) {
        User user = userService.getUserByUserId(userId);
        return new ResultUtil<User>().setData(user);
    }

    @RequestMapping("/getUserByUserName")
    @ResponseBody
    public Result<User> getUserByUserName(String userName) {
        User user = userService.getUserByUserName(userName);
        return new ResultUtil<User>().setData(user);
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public Result<List<User>> getAllUser() {
        List<User> userList = userService.getAllUser();
        return new ResultUtil<List<User>>().setData(userList);
    }

    @RequestMapping("/addOneUser")
    @ResponseBody
    public Result<Integer> addOneUser(String userName) {
        logger.info("userName={}", userName);
        Integer userId = userService.addOneUser(userName);
        return new ResultUtil<Integer>().setData(userId);
    }

    @RequestMapping("/deleteOneUser")
    @ResponseBody
    public Result<Boolean> deleteOneUser(int userId) {
        logger.info("userId={}", userId);
        userService.deleteByUserId(userId);
        return new ResultUtil<Boolean>().setData(true);
    }

    @RequestMapping("/deleteAllUser")
    @ResponseBody
    public Result<Boolean> deleteAllUser() {
        userService.deleteAllUser();
        return new ResultUtil<Boolean>().setData(true);
    }

    @RequestMapping("/updateOneUser")
    @ResponseBody
    public Result<Boolean> updateOneUser(int userId, String userName) {
        logger.info("userId={}, userName={}", userId, userName);
        userService.updateOneUser(userId, userName);
        return new ResultUtil<Boolean>().setData(true);
    }

}
