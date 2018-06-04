package com.zbw.producAndCustomer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 */
public class Producer implements Runnable{

    public static AtomicInteger count = new AtomicInteger(0);

    Repertory repertory = null;

    public Producer(Repertory repertory) {
        this.repertory = repertory;
    }


    @Override
    public void run() {
        while (true){
            synchronized (Product.class){
                count.incrementAndGet();
                Product p = new Product(count.get());
                repertory.push(p,Thread.currentThread().getName());
            }
        }
    }
}
