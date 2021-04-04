package cn.oneplustow.common.web;

import cn.oneplustow.common.web.enume.HttpCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult {

	/**
	 * 是否成功
	 */
	private boolean success = false;

	/**
	 * 失败或成功的提示信息
	 */
	private String message;

	/**
	 * 返回的数据
	 */
	private Object data;

	/**
	 * 返回结果Http状态
	 */
	private HttpCode status;


	public static RestResult renderSuccess() {
		return renderSuccess(null, null);
	}

	public static RestResult renderSuccess(String msg) {
		return renderSuccess(msg, null);
	}

	public static RestResult renderSuccess(Object data) {
		return renderSuccess(null, data);
	}

	public static RestResult renderSuccess(String msg, Object data) {
		RestResult result = new RestResult(true, msg, data, HttpCode.OK);
		return result;
	}

	public static RestResult renderFailure() {
		return renderFailure(null, null);
	}

	public static RestResult renderFailure(String msg) {
		return renderFailure(msg, null);
	}

	public static RestResult renderFailure(Object data) {
		return renderFailure(null, data);
	}

	public static RestResult renderFailure(String msg, Object data) {
		RestResult result = new RestResult(false, msg, data, HttpCode.OK);
		return result;
	}

	public static RestResult renderException() {
		return renderException(null, null);
	}

	public static RestResult renderException(String msg) {
		return renderException(msg, null);
	}

	public static RestResult renderException(Object data) {
		return renderException(null, data);
	}

	public static RestResult renderException(String msg, Object data) {
		RestResult result = new RestResult(false, msg, data, HttpCode.INTERNAL_SERVER_ERROR);
		return result;
	}

}
