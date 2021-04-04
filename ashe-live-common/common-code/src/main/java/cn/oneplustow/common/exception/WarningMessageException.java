package cn.oneplustow.common.exception;

/**
 * @author cc
 */
public class WarningMessageException extends RuntimeException{

    private static final long serialVersionUID = 5587059023340853554L;

    public WarningMessageException() {
        super();
    }

    public WarningMessageException(String message) {
        super(message);
    }

    public WarningMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarningMessageException(Throwable cause) {
        super(cause);
    }

    protected WarningMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
