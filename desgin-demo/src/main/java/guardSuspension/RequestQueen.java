package guardSuspension;

import java.util.LinkedList;

public class RequestQueen {

    private LinkedList queue = new LinkedList();

    public synchronized Request getRequest(){
        while (queue.size() == 0){
            try {
                wait();         //等待，直到有新的request加入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //返回队列中第一个请求
        return (Request) queue.remove();
    }

    public synchronized void addRequest(Request request){
        queue.add(request);
        notifyAll();  //通知 getRequest方法
    }

}
