package com.huangweihan.xweb.controller;

import com.huangweihan.xweb.core.pojo.ResultBean;
import com.huangweihan.xweb.core.utils.RedisUtil;
import com.huangweihan.xweb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/11/1 0001
 */
@RestController
public class CacheController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/setStringCache")
    public ResultBean<Boolean> setStringCache(String key, String value) {
        redisUtil.set(key, value);
        return new ResultBean<>(true);
    }

    @RequestMapping("/getStringCache")
    public ResultBean<String> getStringCache(String key) {
        String value = redisUtil.get(key);
        return new ResultBean<>(value);
    }

    @RequestMapping("/setCache")
    public ResultBean<Boolean> setCache(String key, String value) {
        User user = new User();
        user.setUserId(12);
        user.setUserName(value);
        redisUtil.set(key, user);
        return new ResultBean<>(true);
    }

    @RequestMapping("/getCache")
    public ResultBean<User> getCache(String key) {
        User value = redisUtil.get(key, User.class);
        return new ResultBean<>(value);
    }
}
