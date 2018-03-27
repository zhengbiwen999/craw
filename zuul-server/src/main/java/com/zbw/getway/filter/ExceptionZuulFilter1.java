package com.zbw.getway.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by zbww on 2018/3/27.
 */
@Component
public class ExceptionZuulFilter1 extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 12;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        System.out.println("异常错误展示....");
        dosomeThing();
        return null;
    }

    private void dosomeThing() {
        throw new RuntimeException("出现空针织啦。。。。");
    }
}
