package aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   //注解可以再运行期被JVM读取
@Target({ElementType.METHOD})  //注解只能应用到目标类的方法上
public @interface NeedTest {       //定义注解
    boolean value() default true; //生命注解成员
}
