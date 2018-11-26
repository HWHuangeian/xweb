package com.huangweihan.xweb.core.utils;

/**
 * 参数校验类
 *
 * @author: Administrator
 * @date: 2018/11/23 0023
 */
public class CheckUtil {

    public static void check(String... args) {
        for (String param : args) {
            if (StringUtil.isNullOrEmpty(param)) {
                throw new RuntimeException("param empty");
            }
        }
    }

}
