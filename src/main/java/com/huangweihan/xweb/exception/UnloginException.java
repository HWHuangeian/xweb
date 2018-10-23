package com.huangweihan.xweb.exception;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/18 0018
 */
public class UnloginException extends RuntimeException {

    public UnloginException() {
    }

    public UnloginException(String message) {
        super(message);
    }

    public UnloginException(Throwable cause) {
        super(cause);
    }

    public UnloginException(String message, Throwable cause) {
        super(message, cause);
    }
}
