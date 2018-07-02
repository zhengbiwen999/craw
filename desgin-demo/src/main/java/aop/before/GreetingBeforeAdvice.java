package aop.before;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    /**
     * @param method 目标类方法
     * @param args  目标类方法入参
     * @param obj 目标类实例
     * @throws Throwable
     */
    //在目标类方法调用前执行
    public void before(Method method, Object[] args, Object obj) throws Throwable {
        String clientName = (String) args[0];
        System.out.println("How are you! Mr."+clientName+".");
    }
}
