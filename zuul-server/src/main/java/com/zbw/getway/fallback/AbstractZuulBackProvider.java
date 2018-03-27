package com.zbw.getway.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class AbstractZuulBackProvider implements FallbackProvider {

    // https://www.jianshu.com/p/632f26892c44
    // https://www.cnblogs.com/yjmyzz/p/8093462.html
    //http://dockone.io/article/482
    // http://blog.springcloud.cn/sc/sc-lx/


    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        if(cause instanceof HystrixTimeoutException){
            //return fallbackResponse(HttpStatus.GETWAY_TIMEOUT);
        }
        return this.fallbackResponse();
    }


    @Override
    public String getRoute() {
        // * 表示为所有微服务提供回退
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream((getRoute() + " is unavailable").getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return  this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {
//                logger.debug("close => threadId:" + Thread.currentThread().getId());
            }
        };
    }
}
