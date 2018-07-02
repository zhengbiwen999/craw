package aop.reflexHandler;

import aop.ForumService;
import aop.ForumServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class ForumServiceProxyTest {

    @Test
    public void proxy() throws InterruptedException {

        //希望被代理的目标业务类
        ForumService target = new ForumServiceImpl();

        //目标业务类和横切代码编制到一起
        PerformanceHandler handler = new PerformanceHandler(target);

        //根据编制了目标类逻辑和性能监控横切逻辑的 InvocationHandler 实例创建代理实例
        //使用jdk的代理，只能为接口创建代理实例
        ForumService proxy = (ForumService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        //调用代理实例
        proxy.removeForm(10);
        System.out.println();
        proxy.removeTopic(1012);

    }

}
