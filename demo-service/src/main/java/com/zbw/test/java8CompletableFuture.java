package com.zbw.test;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 场景：
 *  微服务中，比如一个商品详情，可能会并发调用 商品微服务，评论微服务
 */
public class java8CompletableFuture {

    /**
     * Future虽然可以实现获取异步执行结果的需求，但是它没有提供通知的机制，我们无法得知Future什么时候完成。
       要么使用阻塞，在future.get()的地方等待future返回的结果，这时又变成同步操作。
       要么使用isDone()轮询地判断Future是否完成，这样会耗费CPU的资源
     */
    @Test
    public void FutureTest(){
        ExecutorService executorPool = Executors.newFixedThreadPool(2);
        Future<String> future = executorPool.submit(()->{
            System.out.println("running");
            Thread.sleep(10000);
            return "run task ";
        });
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do other things"+future.isDone());
        try {
            System.out.println(future.get(50,TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            executorPool.shutdown();
        }
    }


    /**
     * runAsync 有两个静态的构造方法，无饭回
     */
    @Test
    public void CompletableFutureTest() throws Exception{
        CompletableFuture future = CompletableFuture.runAsync(()->{
            System.out.println("无返回值");
        });
        future.get();  //无返回值
        System.out.println("complete Future");
    }

    /**
     * runAsync 有两个静态的构造方法，有饭回
     */
    @Test
    public void CompletableFutureTest2() throws Exception{
        CompletableFuture future = CompletableFuture.supplyAsync(()->"hello");
        //future.get()在等待执行结果时，程序会一直block，如果此时调用complete(T t)会立即执行,complete(T t)只能调用一次，后续的重复调用会失效
        Object o2 = future.complete("world");
        Object o1 = future.get();
        System.out.println(o1.toString());
        System.out.println("complete Future");
    }


}
