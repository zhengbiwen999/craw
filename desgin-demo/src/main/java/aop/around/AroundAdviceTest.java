package aop.around;

import aop.before.GreetingAfterAdvice;
import aop.before.GreetingBeforeAdvice;
import aop.before.NaiveWaiter;
import aop.before.Waiter;
import net.sf.cglib.proxy.MethodInterceptor;
import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class AroundAdviceTest {

    /**
     * 前置增强
     */
    @Test
    public void before(){

        Waiter target = new NaiveWaiter();

        GreetingAroundAdvice greetingAroundAdvice = new GreetingAroundAdvice();
        //spring 提供的代理工厂,使用 cglib 或 java自带的动态代理
        ProxyFactory factory = new ProxyFactory();

        //设置代理目标
        factory.setTarget(target);
        //使用如下设置，则使用java自带代理,可以设置 optimize
        //factory.setInterfaces(target.getClass().getInterfaces());

        //为代理目标添加增强，可以多个add，形成增强链
        factory.addAdvice(0,greetingAroundAdvice);
        //生成代理实例
        Waiter proxy = (Waiter) factory.getProxy();
        proxy.greetTo("John");
        proxy.serverTo("Tom");

    }

}
