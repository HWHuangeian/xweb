package com.huangweihan.xweb.core.utils;

/**
 * String工具类
 *
 * @author: Administrator
 * @date: 2018/11/23 0023
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

}
