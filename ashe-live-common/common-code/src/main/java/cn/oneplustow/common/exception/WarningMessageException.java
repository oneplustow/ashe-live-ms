package cn.oneplustow.common.exception;

/**
 * @author cc
 */
public class WarningMessageException extends CustomException{

    private static final long serialVersionUID = 5587059023340853554L;


    public WarningMessageException(String message) {
        super(message);
    }

    public WarningMessageException(String message, Integer code) {
        super(message, code);
    }

    public WarningMessageException(String message, Throwable e) {
        super(message, e);
    }
}
