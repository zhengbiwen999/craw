package aop;

public class MethodPerformance {

    private long begin;
    private long end;
    private String serviceMethodId;


    public MethodPerformance(String serviceMethodId) {
        this.serviceMethodId = serviceMethodId;
        this.begin = System.currentTimeMillis();
    }

    public void printPerformance(){
        end = System.currentTimeMillis();
        long elapse = end - begin;
        System.out.println(serviceMethodId+"花费"+elapse+"毫秒");
    }

}
