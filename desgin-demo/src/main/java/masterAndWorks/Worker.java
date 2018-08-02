package masterAndWorks;

import java.util.Map;
import java.util.Queue;

public class Worker implements Runnable {

    protected Queue<Object> workQueen;

    protected Map<String,Object> resultMap;


    public void setWorkQueen(Queue<Object> workQueen) {
        this.workQueen = workQueen;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //子任务处理的逻辑，在子类中具体实现
    public Object handle(Object input) throws Exception{
        return input;
    }

    @Override
    public void run() {
        while (true){
            //获取子任务
            Object input = workQueen.poll();
            if(input == null) break;
            Object re = null;
            try {
                re = handle(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultMap.put(Integer.toString(input.hashCode()),re);
        }
    }
}
