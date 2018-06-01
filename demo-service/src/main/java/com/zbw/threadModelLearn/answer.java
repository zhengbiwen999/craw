package com.zbw.threadModelLearn;

public class answer {
    static boolean answerReady = false;
    static int answer = 0;

    static Thread t1 = new Thread(){
        @Override
        public void run() {
           answer = 22;
           answerReady = true;
        }
    };

    static Thread t2 = new Thread(){
        @Override
        public void run() {
            if(answerReady){
                System.out.println("is : "+answer);
            }else {
                System.out.println("dont know answer");
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        t1.start();t2.start();
        t1.join();t2.join();
    }


}
