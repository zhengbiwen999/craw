package factory.simpleFactory.server.sonFactory;

import factory.simpleFactory.server.LearnLFStudent;
import factory.simpleFactory.server.base.LFEntity;
import factory.simpleFactory.server.base.LFFactory;

/**
 * Created by zbww on 2018/6/26.
 */
public class StudentFactoruy implements LFFactory {

    public LFEntity createLF() {
        return new LearnLFStudent();
    }

}
