package com.zbw.threadModelLearn;

public class demo1 {

    public static void main(String[] args) throws InterruptedException {

        Thread myThread = new Thread(){
            @Override
            public void run() {
                System.out.println("hello from thread");
            }
        };
        //进入就绪状态
        myThread.start();
        Thread.yield();
        System.out.println("hello from main");
        myThread.join();
    }


}
