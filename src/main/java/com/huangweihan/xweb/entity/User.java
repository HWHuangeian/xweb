package com.huangweihan.xweb.entity;

import java.io.Serializable;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/16 0016
 */
public class User implements Serializable {

    private int userId;
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
