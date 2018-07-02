package aop.reflexHandler;

import aop.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {

    private Object target;

    public PerformanceHandler(Object target) {   //目标业务类
        this.target = target;
    }

    //此处为代码监视的横切代码
    /**
     * @param proxy 最终生成的代理实例，一般不会用到
     * @param method 被代理目标实例的某个具体方法，通过它可以发起目标实例方法的反射调用
     * @param args 被代理实例某个方法的入参，在反射调用时使用
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        PerformanceMonitor.begin(target.getClass().getName()+"."+method.getName());

        Object obj = method.invoke(target,args);   //通过反射类调用业务类的目标方法

        PerformanceMonitor.end();

        return obj;
    }
}
