package factory.abstractFactory.creater.createImpl;

import factory.abstractFactory.AbstractFactoryA;
import factory.abstractFactory.AbstractFactoryB;

/**
 * Created by zbww on 2018/6/26.
 */
public class ProductB1 extends AbstractFactoryB {
    public void doThing() {
        System.out.println("生产产品B1");
    }
}
