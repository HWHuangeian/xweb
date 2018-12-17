package com.huangweihan.xweb.controller;

import com.huangweihan.xweb.core.pojo.ResultBean;
import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    @RequestMapping("/user/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }

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
