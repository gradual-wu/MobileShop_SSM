package cn.wycclub.exception;


public class OrderCreateException extends RuntimeException {
    public OrderCreateException() {
    }

    public OrderCreateException(String message) {
        super(message);
    }

    public OrderCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderCreateException(Throwable cause) {
        super(cause);
    }

    public OrderCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
