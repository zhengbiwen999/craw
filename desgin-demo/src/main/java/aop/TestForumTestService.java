package aop;

import org.junit.Test;

public class TestForumTestService {

    @Test
    public void test1() throws InterruptedException {

         ForumService forumService = new ForumServiceImpl();

        forumService.removeForm(10);

        forumService.removeTopic(1012);
    }
}