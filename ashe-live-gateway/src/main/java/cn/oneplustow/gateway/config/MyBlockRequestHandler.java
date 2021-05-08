package cn.oneplustow.gateway.config;

import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.common.enume.HttpCode;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author cc
 * @date 2020/10/27 17:38
 */
@Component
public class MyBlockRequestHandler implements BlockRequestHandler {

    private static final String DEFAULT_BLOCK_MSG_PREFIX = "Blocked by Sentinel: ";

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {
        return this.acceptsHtml(exchange) ? this.htmlErrorResponse(ex) : ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(this.buildErrorResult(ex)));
    }

    private Mono<ServerResponse> htmlErrorResponse(Throwable ex) {
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS).contentType(MediaType.TEXT_PLAIN).bodyValue(DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName());
    }

    private AjaxResult buildErrorResult(Throwable ex) {
        return AjaxResult.error(HttpCode.TOO_MANY_REQUESTS.code(),DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName());
    }

    private boolean acceptsHtml(ServerWebExchange exchange) {
        try {
            List<MediaType> acceptedMediaTypes = exchange.getRequest().getHeaders().getAccept();
            acceptedMediaTypes.remove(MediaType.ALL);
            MediaType.sortBySpecificityAndQuality(acceptedMediaTypes);
            Stream<MediaType> stream = acceptedMediaTypes.stream();
            MediaType texthtml = MediaType.TEXT_HTML;
            return stream.anyMatch(texthtml::isCompatibleWith);
        } catch (InvalidMediaTypeException var3) {
            return false;
        }
    }
}
