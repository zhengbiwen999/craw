package aop.annotation;


public class ForumTestService {

     @NeedTest(value = true)
     public void deleteTopic(int topicId) throws InterruptedException {

          System.out.println("模拟删除topic记录:"+topicId);

     }

     @NeedTest(value = false)
     public void deleteForm(int forumId) throws InterruptedException {
          System.out.println("模拟删除topic记录:"+forumId);
     }



}
