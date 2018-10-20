package com.huangweihan.xweb.exception;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/18 0018
 */
public class CheckException extends RuntimeException {

    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
