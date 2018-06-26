package factory.abstractFactory;

/**
 * Created by zbww on 2018/6/26.
 */
public abstract class AbstractCreatorFactory {

    //产品A 家族
    public abstract AbstractFactoryA createProductA();

    //产品B 家族
    public abstract AbstractFactoryB createProductB();
}
