package cn.oneplustow.common.exception;

public class OrderException extends CustomException {
    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Integer code) {
        super(message, code);
    }

    public OrderException(String message, Throwable e) {
        super(message, e);
    }
}
