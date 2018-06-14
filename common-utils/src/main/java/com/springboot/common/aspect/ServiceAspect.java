package com.springboot.common.aspect;


import com.springboot.common.aspect.filter.ServiceFilter;
import com.springboot.common.aspect.filter.impl.LogFilterImpl;
import com.springboot.common.aspect.filter.impl.MasterSlaveDbFilterImpl;
import com.springboot.common.aspect.filter.impl.RollbackFilterImpl;
import com.springboot.common.db.DbContextHolder;
import com.springboot.utils.error.ThrowableUtil;
import com.springboot.utils.json.FastJsonUtil;
import com.springboot.utils.json.JsonUtil;
import com.springboot.utils.trace.TraceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect implements InitializingBean {

    private ServiceFilter errorFilter;

    private ServiceFilter rollbackFilter;

    private ServiceFilter firstFilter;

//    private ServiceFilter secondFilter;

    private ServiceFilter lastFilter;

    private static final JsonUtil JSON_UTIL = new FastJsonUtil();

    @Around("target(com.springboot.common.AbstractService)")
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        String prefix = "." + pjp.getSignature().toShortString().replace("()", "").replace("(..)", "").replace("..", ".");
        boolean success = true;

//        LogData logData = new LogData();
//        logData.setModule((appConfig.getModule()!= null) ? appConfig.getModule() : appServiceConfig.getShortName());
//        logData.setBusiness(appConfig.getBusiness());
//        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        Method method = signature.getMethod();
//        logData.setRestUrl(prefix);
//        logData.setInvocation(method.toString());
//
//        try {
//            logData.setRequestId(TraceUtil.INSTANCE.getRequestId());
//            logData.setQueryString(Arrays.toString(pjp.getArgs()));
//            if(pjp.getArgs()[0] instanceof CommonRequest)
//            {
//                CommonRequest ob=(CommonRequest)pjp.getArgs()[0];
//                logData.setShopid(ob.getShopGUID());
//                logData.setData(ob.getShopName());
//            }
//        } catch (Exception es) {
//            // Do nothing.
//        }

        try {
//            ((SignCheckFilterImpl) firstFilter).setAppConfig(appConfig);
//            ((SignCheckFilterImpl) firstFilter).setAppKeyConfig(appKeyConfig);

            firstFilter.setNextFilter(lastFilter);
//            fourthFilter.setNextFilter(lastFilter);
            Object result = firstFilter.process(pjp, start, null);
//            logData.setResult(JSON_UTIL.toJson(result));
            return result;
        } catch (DataAccessException de) {
//            logData.setErrorStack(ThrowableUtil.getStackTrace(de));
            rollbackFilter.process(pjp, start, de);
            success = false;
            throw de;
        } catch (Exception e) {
//            logData.setErrorStack(ThrowableUtil.getStackTrace(e));
            success = false;
            return errorFilter.process(pjp, start, e);
        } finally {

//            logData.setElapsedMilliseconds(System.currentTimeMillis() - start);

            DbContextHolder.clearDbType();
            TraceUtil.INSTANCE.clear();
//            if (appConfig.isMetricEnable()) {
//                if (statsDClient == null) {
//                    statsDClient = new NonBlockingStatsDClient(appConfig.getMetricsPrefix(), appConfig.getMetricsHost(), appConfig.getMetricsPort());
//                }
//                if (success) {
//                    statsDClient.increment(prefix + ".success");
//                } else {
//                    statsDClient.increment(prefix + ".fail");
//                }
//                long end = System.currentTimeMillis();
//                statsDClient.recordExecutionTime(prefix + ".time", (end - start), 1);
//            }
//
//            if(success) {
//                LOGGER.info(logData);
//            } else {
//                LOGGER.error(logData);
//            }

        }
    }



    @Override
    public void afterPropertiesSet() throws Exception {

        rollbackFilter = new RollbackFilterImpl();

        firstFilter = new MasterSlaveDbFilterImpl();

        lastFilter = new LogFilterImpl();

        firstFilter.setNextFilter(lastFilter);

    }
}
