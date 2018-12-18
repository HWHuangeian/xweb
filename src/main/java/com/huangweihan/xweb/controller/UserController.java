package com.huangweihan.xweb.controller;

import com.huangweihan.xweb.core.pojo.ResultBean;
import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author Administrator
 * @date 2018-10-16
 */
@RestController
@Api(description = "用户操作类")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/user/login")
    @ApiOperation(value = "用户登录")
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
    @ApiOperation(value = "通过用户名找到用户")
    public ResultBean<User> getUserByUserName(String username) {
        return new ResultBean<>(userService.getUserByUserName(username));
    }

    @RequestMapping("/getAllUser")
    @ApiOperation(value = "获取所有用户")
    public ResultBean<List<User>> getAllUser() {
        return new ResultBean<>(userService.getAllUser());
    }

    @RequestMapping("/addOneUser")
    @ApiOperation(value = "新增一个用户")
    public ResultBean<Integer> addOneUser(String username) {
        logger.info("username={}", username);
        return new ResultBean<>(userService.addOneUser(username));
    }

    @RequestMapping("/deleteOneUser")
    @ApiOperation(value = "删除一个用户")
    public ResultBean<Boolean> deleteOneUser(String username) {
        logger.info("username={}", username);
        userService.deleteByUserName(username);
        return new ResultBean<>(true);
    }

    @RequestMapping("/deleteAllUser")
    @ApiOperation(value = "删除所有用户")
    public ResultBean<Boolean> deleteAllUser() {
        userService.deleteAllUser();
        return new ResultBean<>(true);
    }

}
