package com.zbw.lockLearn;

import java.util.concurrent.locks.ReentrantLock;

public class demo1 {
    /**
     * 使用 lock 解决 内置锁 死锁，只能终止 jvm
     */
    public static void main(String[] args) throws Exception {

        final ReentrantLock l1 = new ReentrantLock();
        final ReentrantLock l2 = new ReentrantLock();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("开始执行l1");
                try {
                    l1.lockInterruptibly();
                    Thread.sleep(1000);
                    l2.lockInterruptibly();
                } catch (Exception e) {
                    System.out.println("l1 interrupt");
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("开始执行l2");
                    l2.lockInterruptibly();
                    Thread.sleep(1000);
                    l1.lockInterruptibly();
                } catch (Exception e) {
                    System.out.println("l2 interrupt");
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t2.start();
        Thread.sleep(2000);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }

}
