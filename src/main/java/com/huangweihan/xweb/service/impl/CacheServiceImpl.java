package com.huangweihan.xweb.service.impl;

import com.huangweihan.xweb.core.utils.RedisUtil;
import com.huangweihan.xweb.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/11/1 0001
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取数据
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCache(String key) {
        return (T) redisUtil.getDataFromCache(key);
    }

    /**
     * 清除缓存
     */
    @Override
    public void removeCache(String key) {
        redisUtil.clearCache(key);
    }

    /**
     * 放入缓存
     */
    @Override
    public <T> void setCache(String key, T value) {
        redisUtil.setDataToCache(key, value);
    }

}
