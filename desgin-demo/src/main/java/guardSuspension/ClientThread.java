package guardSuspension;

public class ClientThread extends Thread {

    private RequestQueen requestQueen;

    public ClientThread(RequestQueen requestQueen,String name) {
        super(name);
        this.requestQueen = requestQueen;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){

            Request request = new Request("RequestId:"+i+"Thread_name:"+ ClientThread.currentThread().getName());

            requestQueen.addRequest(request);
            try {
                //客户端请求速度
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ClientThread Name is :"+Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName()+" request end");
    }
}
