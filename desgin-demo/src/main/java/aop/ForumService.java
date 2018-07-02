package aop;

public interface ForumService {

     void removeTopic(int topicId) throws InterruptedException;

     void removeForm(int forumId) throws InterruptedException;
}
