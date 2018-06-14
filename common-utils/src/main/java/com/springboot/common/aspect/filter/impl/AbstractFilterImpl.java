package com.springboot.common.aspect.filter.impl;

import com.springboot.common.AbstractSignRequest;
import com.springboot.common.aspect.filter.ServiceFilter;
import com.springboot.utils.json.FastJsonUtil;
import com.springboot.utils.log.MwLogger;
import com.springboot.utils.trace.TraceUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

public abstract class AbstractFilterImpl implements ServiceFilter{

    private ServiceFilter nextFilter;

    protected FastJsonUtil jsonUtil = new FastJsonUtil();

    protected final Logger logger = new MwLogger(getClass());

    @Override
    public Object process(ProceedingJoinPoint point, long start, Exception e) throws Throwable {
        return nextFilter.process(point,start,e);
    }

    @Override
    public ServiceFilter getNextFilter() {
        return nextFilter;
    }

    @Override
    public void setNextFilter(ServiceFilter filter) {
        nextFilter = filter;
    }

    protected void setRequestId(Object arg) {
        if (arg instanceof AbstractSignRequest) {
            AbstractSignRequest request = (AbstractSignRequest) arg;
            if (StringUtils.isBlank(request.getRequestId())) {
                request.setRequestId(TraceUtil.INSTANCE.getRequestId());
            } else {
                TraceUtil.INSTANCE.setRequestId(request.getRequestId());
            }
        }
    }

    protected void buildArgsLog(Object[] args, StringBuilder sb) {
        if (args == null || args.length <= 0) {
            sb.append("null,");
            return;
        }
        for (Object o : args) {
            setRequestId(o);
            if (o != null) {
                if (o instanceof AbstractSignRequest) {
                    sb.append(o.getClass().getSimpleName() + ": " + jsonUtil.toJson(o) + ",");
                } else {
                    sb.append(o.getClass().getSimpleName() + ": " + o.toString() + ",");
                }
            } else {
                sb.append("null: null");
            }
        }
    }



}
