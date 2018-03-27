package com.zbw.getway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关可以做非业务性质的校验，可以让微服务应用端去除各种复杂的过滤器和拦截器，更关注业务
 * 必须继承 ZuulFilter
 */
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否需要执行
     */
    @Override
    public boolean shouldFilter() {
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
