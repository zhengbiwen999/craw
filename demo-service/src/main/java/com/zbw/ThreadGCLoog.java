package com.zbw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zbww on 2018/6/29.
 */
public class ThreadGCLoog {

    /**
     * 线程死循环
     */
    public static void createThreadLoog(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    ;
            }
        },"testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待
     */
    public static void createLookThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread.sleep(10000);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createThreadLoog();
        br.readLine();
        Object obj = new Object();
        createLookThread(obj);
    }

}
