package aop.around;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingAroundAdvice implements MethodInterceptor {

    /**
     * 环绕增强
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //目标方法入参
        Object[] args = invocation.getArguments();
        String clientName = (String) args[0];
        System.out.println("How are you! Mr."+clientName+".");

        Object obj = invocation.proceed();
        System.out.println("plz enjoy yourself");
        return obj;
    }
}
