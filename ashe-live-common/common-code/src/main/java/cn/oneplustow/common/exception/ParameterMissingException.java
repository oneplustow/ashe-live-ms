package cn.oneplustow.common.exception;

/**
 * @author cc
 */
public class ParameterMissingException extends CustomException {
    public ParameterMissingException(String message) {
        super(message);
    }

    public ParameterMissingException(String message, Integer code) {
        super(message, code);
    }

    public ParameterMissingException(String message, Throwable e) {
        super(message, e);
    }
}
