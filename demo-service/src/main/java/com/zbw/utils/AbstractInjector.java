package com.zbw.utils;

import com.springboot.utils.json.FastJsonUtil;
import com.springboot.utils.json.JsonUtil;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public abstract class AbstractInjector {

    protected RetryTemplate getRetryTemplate() {
        RetryTemplate template = new RetryTemplate();
        TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
        policy.setTimeout(100);
        template.setRetryPolicy(policy);
        return template;
    }

    protected JsonUtil getJsonUtil() {
        return new FastJsonUtil();
    }
}
