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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserByUserName")
    @ResponseBody
    public ResultBean<User> getUserByUserName(String username) {
        return new ResultBean<>(userService.getUserByUserName(username));
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public ResultBean<List<User>> getAllUser() {
        return new ResultBean<>(userService.getAllUser());
    }

    @RequestMapping("/addOneUser")
    @ResponseBody
    public ResultBean<Integer> addOneUser(String username) {
        logger.info("username={}", username);
        return new ResultBean<>(userService.addOneUser(username));
    }

    @RequestMapping("/deleteOneUser")
    @ResponseBody
    public ResultBean<Boolean> deleteOneUser(String username) {
        logger.info("username={}", username);
        userService.deleteByUserName(username);
        return new ResultBean<>(true);
    }

    @RequestMapping("/deleteAllUser")
    @ResponseBody
    public ResultBean<Boolean> deleteAllUser() {
        userService.deleteAllUser();
        return new ResultBean<>(true);
    }

}
