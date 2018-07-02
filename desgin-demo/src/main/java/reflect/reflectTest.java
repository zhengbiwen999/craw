package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class reflectTest {


    public static Car initByReflect() throws Exception {

        //1、通过类加载器获取Car类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loader.loadClass("reflect.Car");

        //2、获取类的默认构造器对象，并通过它实力化Car
        Constructor<?> cons = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) cons.newInstance();

        //3、通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car,"奔驰");

        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car,"黑色");

        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car,200);

        return car;
    }

    public static void main(String[] args) throws Exception {
        Car car = initByReflect();
        car.introduce();
    }


    @Test
    public void test1(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader:"+loader);
        System.out.println("parent loader:"+loader.getParent());
        System.out.println("爷爷 loader:"+loader.getParent().getParent());
    }

}
