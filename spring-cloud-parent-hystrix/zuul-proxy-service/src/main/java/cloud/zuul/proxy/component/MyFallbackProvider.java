package cloud.zuul.proxy.component;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @apiNote 网关失败回调
 * getRoute()	该Provider应用的Route ID，例如：testservice，如果设置为 * ，那就对所有路由生效
 * fallbackResponse(String route, Throwable cause)	快速回退失败/响应，即处理异常并返回对应输出/响应内容。route：发生异常的RouteID，cause：触发快速回退/失败的异常/错误
 * ClientHttpResponse	Spring提供的HttpResponse接口。可以通过实现该接口自定义Http status、body、header
 */
@Slf4j
@Component
public class MyFallbackProvider implements FallbackProvider  {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.warn(String.format("route:%s,exceptionType:%s, route, cause.getClass().getName()"));
        log.error("message={}", cause.getMessage(), cause);
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return status;
            }

            @Override
            public int getRawStatusCode() {
                return status.value();
            }

            @Override
            public String getStatusText() {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() {
                String msg = String.format("{\"code\": 1103,\"message\": \"%s\"}", status.value());
                return new ByteArrayInputStream(msg.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }

}
