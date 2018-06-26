package factory.abstractFactory.creater;

import factory.abstractFactory.AbstractCreatorFactory;
import factory.abstractFactory.AbstractFactoryA;
import factory.abstractFactory.AbstractFactoryB;
import factory.abstractFactory.creater.createImpl.ProductA2;
import factory.abstractFactory.creater.createImpl.ProductB2;

/**
 * Created by zbww on 2018/6/26.
 */
public class Creater2 extends AbstractCreatorFactory {

    public AbstractFactoryA createProductA() {
        return new ProductA2();
    }

    public AbstractFactoryB createProductB() {
        return new ProductB2();
    }
}
