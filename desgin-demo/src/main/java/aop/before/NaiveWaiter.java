package aop.before;

public class NaiveWaiter implements Waiter {
    public void greetTo(String name) {
        System.out.println("greet to "+name+"....");
    }

    public void serverTo(String name) {
        System.out.println("serving to "+name+"....");
    }
}
