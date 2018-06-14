package com.springboot.common.aspect.filter.impl;

import com.springboot.common.aspect.filter.ServiceFilter;
import com.springboot.utils.error.ThrowableUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


public class RollbackFilterImpl extends AbstractFilterImpl implements ServiceFilter {

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
        sb.append("error=>" + ThrowableUtil.getStackTrace(e));
        logger.info(sb.toString());
        return null;
    }
}
