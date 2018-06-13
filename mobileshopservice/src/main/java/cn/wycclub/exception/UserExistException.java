package cn.wycclub.exception;

/**
 * 用户已存在(异常)
 *
 * @author WuYuchen
 * @create 2018-02-13 23:58
 **/

public class UserExistException extends RuntimeException {

    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }

    public UserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
