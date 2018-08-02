package guardSuspension;

public class main {

    public static void main(String[] args) {
        RequestQueen requestQueen = new RequestQueen();

        for(int i=1;i<10;i++){
            new ServerThread(requestQueen,"ServerThread"+i).start();
        }

        for(int i=1;i<10;i++){
            new ClientThread(requestQueen,"ClientThread"+i).start();
        }
    }

}
