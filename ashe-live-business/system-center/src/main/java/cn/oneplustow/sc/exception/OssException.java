package cn.oneplustow.sc.exception;

/**
 * OSS异常类
 *
 */
public class OssException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OssException(String msg) {
		super(msg);
	}

	public OssException() {
		super();
	}

	public OssException(String message, Throwable cause) {
		super(message, cause);
	}

	public OssException(Throwable cause) {
		super(cause);
	}

	protected OssException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
