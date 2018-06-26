package factory.simpleFactory.server;

import factory.simpleFactory.server.base.LFEntity;

/**
 * Created by zbww on 2018/6/26.
 */
public class LearnLFStudent extends LFEntity {

    @Override
    public void wash() {
        String who = "学生";
        System.out.println(who+"洗衣服");
    }

}
