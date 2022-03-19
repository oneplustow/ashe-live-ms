package cn.oneplustow.common.domain;

import cn.hutool.http.HttpStatus;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作消息提醒
 *
 * @author Lion Li
 */
@Data
public class AjaxResult<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 返回内容
	 */
	private String msg;

	/**
	 * 数据对象
	 */
	private T data;

	public AjaxResult() {
	}

	public AjaxResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 初始化一个新创建的 AjaxResult 对象
	 *
	 * @param code 状态码
	 * @param msg  返回内容
	 */
	public AjaxResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 返回成功消息
	 *
	 * @return 成功消息
	 */
	public static AjaxResult<Void> success() {
		return AjaxResult.success("操作成功");
	}

	public void put(String key,Object value) {
		T data = getData();
		if(data == null){
			data = (T) new HashMap<String,Object>(8);
			setData(data);
		}
		if(data instanceof Map){
			((Map)data).put(key,value);
		}
	}


	/**
	 * 返回成功数据
	 *
	 * @return 成功消息
	 */
	public static <T> AjaxResult<T> success(T data) {
		return AjaxResult.success("操作成功", data);
	}

	/**
	 * 返回成功消息
	 *
	 * @param msg 返回内容
	 * @return 成功消息
	 */
	public static AjaxResult<Void> success(String msg) {
		return AjaxResult.success(msg, null);
	}

	/**
	 * 返回成功消息
	 *
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 成功消息
	 */
	public static <T> AjaxResult<T> success(String msg, T data) {
		return new AjaxResult<>(HttpStatus.HTTP_OK, msg, data);
	}

	/**
	 * 返回错误消息
	 *
	 * @return
	 */
	public static AjaxResult<Void> error() {
		return AjaxResult.error("操作失败");
	}

	/**
	 * 返回错误消息
	 *
	 * @param msg 返回内容
	 * @return 警告消息
	 */
	public static AjaxResult<Void> error(String msg) {
		return AjaxResult.error(msg, null);
	}

	/**
	 * 返回错误消息
	 *
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 警告消息
	 */
	public static <T> AjaxResult<T> error(String msg, T data) {
		return new AjaxResult<>(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
	}

	/**
	 * 返回错误消息
	 *
	 * @param code 状态码
	 * @param msg  返回内容
	 * @return 警告消息
	 */
	public static AjaxResult<Void> error(int code, String msg) {
		return new AjaxResult<>(code, msg, null);
	}

}
