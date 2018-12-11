package com.huangweihan.xweb.entity;

import java.io.Serializable;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/16 0016
 */
public class User implements Serializable {

    private int id;
    private String userName;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
