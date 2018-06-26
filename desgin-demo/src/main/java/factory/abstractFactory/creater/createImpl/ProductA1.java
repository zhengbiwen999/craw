package factory.abstractFactory.creater.createImpl;

import factory.abstractFactory.AbstractFactoryA;

/**
 * Created by zbww on 2018/6/26.
 */
public class ProductA1 extends AbstractFactoryA {
    public void doThing() {
        System.out.println("生产产品A1");
    }
}
