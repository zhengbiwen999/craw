package com.zbw.producAndCustomer;

import java.util.LinkedList;

public class Repertory {

//    private BlockingQueue<Product> queue;

    //定义一个集合类用于存放产品.规定仓库的最大容量为10.
    public LinkedList<Product> store = new LinkedList<Product>();

    public LinkedList<Product> getStore() {
        return store;
    }

    public void setStore(LinkedList<Product> store) {
        this.store = store;
    }

    public synchronized void push(Product p, String threadName) {
        while (store.size() == 10) {
            try {
                //打印日志
                System.out.println(threadName + "仓库已满，请等待消费");
                //因为仓库容量已满，无法继续生产，进入等待状态，等待其他线程唤醒.
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //唤醒所有等待线程
        this.notifyAll();
        //将产品添加到仓库中.
        store.addLast(p);
        //打印生产日志
        System.out.println(threadName + "生产了:" + p.getId() + "号产品" + " " + "当前库存数量:" + store.size());
        try {
            //为了方便观察结果,每次生产完后等待0.1秒.
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void get(String threadName) {
        while (store.size() == 0) {
            try {
                //打印日志
                System.out.println(threadName + "仓库已空，请等待生产");
                //因为仓库容量已空，无法继续消费，进入等待状态，等待其他线程唤醒.
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //唤醒所有等待线程
        this.notifyAll();
        store.removeFirst(); //方法将产品从仓库中移出.
        //打印日志
        System.out.println(threadName + "消费了" + " " + "当前库存数量:" + store.size());
        try {
            //为了方便观察结果,每次生产完后等待1秒.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
