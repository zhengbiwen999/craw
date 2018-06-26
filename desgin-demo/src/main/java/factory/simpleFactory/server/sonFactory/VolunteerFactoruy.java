package factory.simpleFactory.server.sonFactory;

import factory.simpleFactory.server.Volunteer;
import factory.simpleFactory.server.base.LFEntity;
import factory.simpleFactory.server.base.LFFactory;

/**
 * Created by zbww on 2018/6/26.
 */
public class VolunteerFactoruy implements LFFactory {

    public LFEntity createLF() {
        return new Volunteer();
    }
}
