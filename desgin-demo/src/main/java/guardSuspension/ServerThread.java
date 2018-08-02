package guardSuspension;

public class ServerThread extends Thread {

    private RequestQueen requestQueen;

    public ServerThread(RequestQueen requestQueen,String name) {
        super(name);
        this.requestQueen = requestQueen;
    }

    @Override
    public void run() {
        while (true){
            final Request request = requestQueen.getRequest();
            try {
                //模拟请求处理时间消耗
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 处理请求："+  request);
        }
    }
}
