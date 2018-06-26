package factory.abstractFactory;

import factory.abstractFactory.creater.Creater1;
import factory.abstractFactory.creater.Creater2;

/**
 * Created by zbww on 2018/6/26.
 */
public class main {

    public static void main(String[] args) {
        AbstractCreatorFactory creator1 = new Creater1();
        AbstractCreatorFactory creator2 = new Creater2();
        AbstractFactoryA a1 = creator1.createProductA();
        AbstractFactoryA a2 = creator2.createProductA();

        AbstractFactoryB b1 = creator1.createProductB();
        AbstractFactoryB b2 = creator2.createProductB();

        a1.doThing();
        a2.doThing();
        b1.doThing();
        b2.doThing();
    }





}
