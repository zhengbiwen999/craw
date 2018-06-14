package com.springboot.common.aspect.filter.impl;

import com.springboot.common.DataResult;
import com.springboot.utils.trace.TraceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


public class LogFilterImpl extends AbstractFilterImpl {


    @Override
    public Object process(ProceedingJoinPoint point, long start, Exception e) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        //记录参数及输出结果
        Object[] args = point.getArgs();
        StringBuilder sb = new StringBuilder();
        sb.append("method=>" + method.toString());
        sb.append(",args=>");

        buildArgsLog(args, sb);

        if (getNextFilter() == null) {
            Object result = point.proceed();
            long end = System.currentTimeMillis();
            if (result != null) {
                if (result instanceof DataResult) {
                    DataResult dr = (DataResult) result;
                    dr.setElapsedMilliseconds(end - start);
                    dr.setRequestId(TraceUtil.INSTANCE.getRequestId());
                    sb.append("result=>" + jsonUtil.toJson(result));
                } else {
                    sb.append("result=>" + result.toString());
                }
            } else {
                sb.append("result=>" + null);
            }
            logger.info(sb.toString());
            return result;
        }

        logger.info(sb.toString());
        return getNextFilter().process(point, start, e);

    }

}
