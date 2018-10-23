package com.huangweihan.xweb.exception;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/10/18 0018
 */
public class NoPermissionException extends RuntimeException {

    public NoPermissionException() {
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
