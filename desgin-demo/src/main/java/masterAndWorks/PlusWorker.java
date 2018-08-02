package masterAndWorks;

public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) throws Exception{
        Thread.sleep(20);
        Integer i = (Integer)input;
        return i*i*i;
    }
}
