package com.zbw.getway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by zbww on 2018/3/27.
 */
@Component
public class ExceptionZuulFilter2 extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        System.out.println("异常错误展示....");

        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            dosomeThing();
        }catch (Exception e){
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception",e);
        }
        return null;
    }

    private void dosomeThing() {
        throw new RuntimeException("出现空针织啦。。。。");
    }
}
