package com.huangweihan.xweb.controller;

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

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public Result<User> hello() {
        log.info("[Entering method] | hello() {}", "coming");
        User user = userService.login();
        return new ResultUtil<User>().setData(user);
    }

}
