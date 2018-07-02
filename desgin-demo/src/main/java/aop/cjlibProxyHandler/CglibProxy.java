package aop.cjlibProxyHandler;

import aop.PerformanceMonitor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);  //设置需要创建子类的类
        enhancer.setCallback(this);
        return enhancer.create();   //通过字节码技术动态创建子类实例
    }

    //拦截父类所有的方法调用
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        PerformanceMonitor.begin(obj.getClass().getName()+"."+method.getName());

        Object result = proxy.invokeSuper(obj,args);  //通过代理类调用父类中的方法

        PerformanceMonitor.end();

        return result;
    }
}
