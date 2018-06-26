package factory.abstractFactory.creater;

import factory.abstractFactory.AbstractCreatorFactory;
import factory.abstractFactory.AbstractFactoryA;
import factory.abstractFactory.AbstractFactoryB;
import factory.abstractFactory.creater.createImpl.ProductA1;
import factory.abstractFactory.creater.createImpl.ProductB1;

/**
 * Created by zbww on 2018/6/26.
 */
public class Creater1 extends AbstractCreatorFactory {

    public AbstractFactoryA createProductA() {
        return new ProductA1();
    }

    public AbstractFactoryB createProductB() {
        return new ProductB1();
    }
}
