package masterAndWorks;


import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Master {

    //任务队列
    protected Queue<Object> workQueen = new ConcurrentLinkedDeque<Object>();

    //worker进站队列
    protected Map<String,Thread> threadMap = new HashMap<String,Thread>();

    //子任务处理结果集
    protected Map<String,Object> resultMap = new ConcurrentHashMap<String,Object>();

    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry : threadMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public Master(Worker worker,int countWorker) {
        worker.setWorkQueen(workQueen);
        worker.setResultMap(resultMap);
        for(int i=0;i<countWorker;i++){
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }
    }

    //提交一个任务
    public void submit(Object job){
        workQueen.add(job);
    }

    //返回子任务结果集
    public Map<String,Object> getResultMap(){
        return resultMap;
    }

    //处理所有worker进程
    public void execute(){
        for(Map.Entry<String,Thread> entry: threadMap.entrySet()){
            entry.getValue().start();
        }
    }


}
