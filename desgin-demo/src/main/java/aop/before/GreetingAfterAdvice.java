package aop.before;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingAfterAdvice implements AfterReturningAdvice {

    /**
     * @param method 目标类方法
     * @param args  目标类方法入参
     * @param obj 目标类实例
     * @throws Throwable
     */
    //在目标类方法调用后执行
    public void afterReturning(Object o, Method method, Object[] args, Object obj) throws Throwable {
        System.out.println("plz enjoy yourself");
    }
}
