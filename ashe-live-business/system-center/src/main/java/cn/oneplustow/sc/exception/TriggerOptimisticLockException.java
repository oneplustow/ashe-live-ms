package cn.oneplustow.sc.exception;

/**
 * @author cc
 */
public class TriggerOptimisticLockException extends RuntimeException{

    private static final long serialVersionUID = 5587059023340853554L;

    public TriggerOptimisticLockException() {
        super();
    }

    public TriggerOptimisticLockException(String message) {
        super(message);
    }

    public TriggerOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public TriggerOptimisticLockException(Throwable cause) {
        super(cause);
    }

    protected TriggerOptimisticLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
