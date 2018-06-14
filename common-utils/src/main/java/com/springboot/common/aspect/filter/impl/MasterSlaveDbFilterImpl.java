package com.springboot.common.aspect.filter.impl;

import com.springboot.common.annotation.Slave;
import com.springboot.common.db.DbContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class MasterSlaveDbFilterImpl extends AbstractFilterImpl{

    @Override
    public Object process(ProceedingJoinPoint point, long start, Exception e) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        //db主从切换
        DbContextHolder.setDbType(DbContextHolder.DbType.MASTER);
        if(method.isAnnotationPresent(Slave.class)){
            DbContextHolder.setDbType(DbContextHolder.DbType.MASTER);
            System.out.println("已切换到从库！");
        }

        if(getNextFilter() == null){
            return point.proceed();
        }

        return getNextFilter().process(point, start, e);
    }


}
