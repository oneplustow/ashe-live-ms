package cn.oneplustow.common.web.enume;
/**
 * @author cc
 */
public enum HttpCode {
	/** 200请求成功 */
	OK(200,"请求成功 "),
	/** 207频繁操作 */
	MULTI_STATUS(207,"频繁操作"),
	/** 303登录失败 */
	LOGIN_FAIL(303,"登录失败"),
	/** 400请求参数出错 */
	BAD_REQUEST(400,"请求参数出错"),
	/** 401没有登录 */
	UNAUTHORIZED(401,"没有登录"),
	/** 403没有权限 */
	FORBIDDEN(403,"没有权限"),
	/** 404找不到页面 */
	NOT_FOUND(404,"找不到页面"),
	/** 408请求超时 */
	REQUEST_TIMEOUT(408,"请求超时"),
	/** 409发生冲突 */
	CONFLICT(409,"发生冲突"),
	/** 410已被删除 */
	GONE(410,"已被删除"),
	/** 423已被锁定 */
	LOCKED(423,"已被锁定"),
	TOO_MANY_REQUESTS(429, "Too Many Requests"),
	/** 500服务器出错 */
	INTERNAL_SERVER_ERROR(500,"服务器内部错误");

	private Integer code;

	private String msg;

	HttpCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer code() {
		return this.code;
	}

	public String msg() { return this.msg;}

	@Override
	public String toString() {
		return this.code.toString() + ":" + this.msg.toString();
	}
}
