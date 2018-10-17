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

    @RequestMapping("/findUserByUserId")
    @ResponseBody
    public Result<User> findUserByUserId(int userId) {
        User user = userService.getUserByUserId(userId);
        logger.info("userId={}, userName={}", user.getUserId(), user.getUserName());
        return new ResultUtil<User>().setData(user);
    }

    @RequestMapping("/findUserByUserName")
    @ResponseBody
    public Result<User> findUserByUserName(String userName) {
        User user = userService.getUserByUserName(userName);
        logger.info("userId={}, userName={}", user.getUserId(), user.getUserName());
        return new ResultUtil<User>().setData(user);
    }

    @RequestMapping("/addOneUser")
    @ResponseBody
    public Result<Boolean> addOneUser(String userName) {
        logger.info("userName={}", userName);
        userService.addOneUser(userName);
        return new ResultUtil<Boolean>().setData(true);
    }

}
