package aop;

public class ForumServiceImpl implements ForumService {

    public void removeTopic(int topicId) throws InterruptedException {
        //PerformanceMonitor.begin("aop.ForumServiceImpl.removeTopic");

        System.out.println("模拟删除topic记录:"+topicId);

        Thread.currentThread().sleep(20);


        //PerformanceMonitor.end();
    }

    public void removeForm(int forumId) throws InterruptedException {
       //PerformanceMonitor.begin("aop.ForumServiceImpl.removeForm");

        System.out.println("模拟删除topic记录:"+forumId);

        Thread.currentThread().sleep(60);

        //PerformanceMonitor.end();
    }
}
