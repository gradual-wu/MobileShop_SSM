package cn.wycclub.exception;

/**
 * @author WuYuchen
 * @Description 插入商品数据异常
 * @Date 2018-03-15 19:52
 */
public class InsertProductException extends RuntimeException {
    public InsertProductException() {
    }

    public InsertProductException(String message) {
        super(message);
    }

    public InsertProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertProductException(Throwable cause) {
        super(cause);
    }

    public InsertProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
