package com.springboot.common.aspect.filter;
import org.aspectj.lang.ProceedingJoinPoint;

public interface ServiceFilter {

    Object process(ProceedingJoinPoint point,long start,Exception e) throws Throwable;

    ServiceFilter getNextFilter();

    void setNextFilter(ServiceFilter filter);

}
