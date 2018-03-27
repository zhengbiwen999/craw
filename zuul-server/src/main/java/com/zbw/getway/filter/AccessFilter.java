package com.zbw.getway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
// 默认实现的核心过滤器
import org.springframework.cloud.netflix.zuul.filters.*;


/**
 * 网关可以做非业务性质的校验，可以让微服务应用端去除各种复杂的过滤器和拦截器，更关注业务
 * 必须继承 ZuulFilter
 */
@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /**
         * pre: 请求路由前调用
         * routing: 路由请求时调用
         * post： routing和error 之间调用
         * error: 发生错误时调用
         */
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        /**
         * 通过数字控制过滤器执行顺序，越小，优先级越高
         */
        return 0;
    }

    /**
     * 过滤器是否需要执行
     */
    @Override
    public boolean shouldFilter() {
        // 可以通过配置 ignoreUrls 来让一些请求不用走网关
        return true;
    }


    /**
     *  过滤器具体逻辑
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String accesToken = request.getParameter("accesToken");
        if (StringUtils.isEmpty(accesToken)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
