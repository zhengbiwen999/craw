package com.zbw.producAndCustomer;

/**
 * 生产者
 */
public class Customer implements Runnable{

    Repertory repertory = null;

    public Customer(Repertory repertory) {
        this.repertory = repertory;
    }

    @Override
    public void run() {
        while (true){
            repertory.get(Thread.currentThread().getName());
        }
    }
}
