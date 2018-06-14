package com.springboot.common.interceptor;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;


@Intercepts(value = {
        @Signature (type=Executor.class,
                method="update",
                args = { MappedStatement.class, Object.class })
})
public class DbInterceptor implements Interceptor {
    private Properties properties;
    private Field additionalParametersField;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
// 只拦截update
//        if (ms.getSqlCommandType()==SqlCommandType.UPDATE) {
        BoundSql boundSql = ms.getBoundSql(parameter);
        Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
//        }
        return invocation.proceed();
    }
    /**
     * <p>
     * 判断该操作是否是update操作
     * </p>
     *
     * @param invocation
     * @return 是否是update操作
     */
    private boolean isUpdateMethod(Invocation invocation) {
        if (invocation.getArgs()[0] instanceof MappedStatement) {
            MappedStatement mappedStatement = (MappedStatement) invocation
                    .getArgs()[0];
            return SqlCommandType.UPDATE.equals(mappedStatement
                    .getSqlCommandType());
        }
        return false;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
        try {
            //反射获取 BoundSql 中的 additionalParameters 属性
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
