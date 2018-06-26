package factory.simpleFactory.server;

import factory.simpleFactory.server.base.LFEntity;
import factory.simpleFactory.server.base.LFFactory;
import factory.simpleFactory.server.sonFactory.StudentFactoruy;
import factory.simpleFactory.server.sonFactory.VolunteerFactoruy;

/**
 * Created by zbww on 2018/6/26.
 */
public class main {

    public static void main(String[] args) {

        LFFactory volunteerFactory = new VolunteerFactoruy();
        LFFactory sgudentFactory = new StudentFactoruy();

        System.out.println("=====   志愿者工厂  =====");
        LFEntity volunteer = volunteerFactory.createLF();
        volunteer.buy();
        volunteer.dish();
        volunteer.wash();
        System.out.println();
        System.out.println("=====   学生工厂  =====");
        System.out.println();
        LFEntity student = sgudentFactory.createLF();
        student.buy();
        student.dish();
        student.wash();

    }

}
