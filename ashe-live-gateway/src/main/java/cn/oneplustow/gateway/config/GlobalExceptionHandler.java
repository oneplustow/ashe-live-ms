package cn.oneplustow.gateway.config;

import cn.hutool.core.bean.BeanUtil;
import cn.oneplustow.common.domain.AjaxResult;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * 自定义异常处理
 * 
 * <p>异常时用JSON代替HTML异常信息<p>
 * 
 * @author cc
 *
 */
public class GlobalExceptionHandler extends DefaultErrorWebExceptionHandler {

	public GlobalExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
								  ErrorProperties errorProperties, ApplicationContext applicationContext) {
		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
	}

	/**
	 * 获取异常属性
	 */
	@Override
	protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		Throwable error = super.getError(request);
		return response(error);
	}

	/**
	 * 指定响应处理方法为JSON处理的方法
	 * @param errorAttributes
	 */
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	/**
	 * 根据code获取对应的HttpStatus
	 * @param errorAttributes
	 */
	@Override
	protected int getHttpStatus(Map<String, Object> errorAttributes) {
		int statusCode = (int) errorAttributes.get("code");
		return statusCode;
	}


	/**
	 * 构建返回的JSON数据格式
	 * @param status		状态码
	 * @param errorMessage  异常信息
	 * @return
	 */
	public static Map<String, Object> response(Throwable error) {
		int status = 500;
		String errorMessage = error.getMessage();
		if(error instanceof ResponseStatusException){
			ResponseStatusException responseStatusException = (ResponseStatusException) error;
			status = responseStatusException.getStatus().value();
			errorMessage = responseStatusException.getReason();
		}
		AjaxResult result = AjaxResult.error(status, errorMessage);
		return BeanUtil.beanToMap(result);
	}

}