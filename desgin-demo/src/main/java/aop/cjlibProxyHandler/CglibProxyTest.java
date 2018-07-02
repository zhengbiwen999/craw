package aop.cjlibProxyHandler;

import aop.ForumServiceImpl;
import org.junit.Test;

public class CglibProxyTest {

    @Test
    public void proxy() throws InterruptedException {

        CglibProxy proxy = new CglibProxy();

        ForumServiceImpl forumService = (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);

        //调用代理实例
        forumService.removeForm(10);
        System.out.println();
        forumService.removeTopic(1012);

    }

}
