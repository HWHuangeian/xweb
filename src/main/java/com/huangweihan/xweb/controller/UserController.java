package com.huangweihan.xweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ResponseBody
    @RequestMapping("/login")
    public String hello() {
        log.info("hello() {}", "coming");
        return "hello";
    }

}
