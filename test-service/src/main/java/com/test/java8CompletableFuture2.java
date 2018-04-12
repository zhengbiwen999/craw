package com.test;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class java8CompletableFuture2 {

    @Test
    public void test1() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello ")
                .thenApply(s -> s + "world")
                .thenApply(String::toUpperCase);

        System.out.println(future.get());
    }


    /**
     * thenAcceptBoth 和 thenCombine 类似，只是返回为 void
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {

//        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
//                                                            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "100"))
//                                                            .thenCompose(s -> CompletableFuture.supplyAsync(() -> Double.parseDouble(s)));
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<Double> future = future1.thenCombine(future2, (i, j) -> Double.parseDouble(i + j));
        System.out.println(future.get());
    }

    /**
     * 计算处理完后，对结果处理
     * whenComplete : 无返回
     */
    @Test
    public void test3() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world")
                .thenApply(s -> s + "\nhaha ")
                .thenApply(String::toUpperCase)
                .whenComplete((result, throwable) -> {
                            if (throwable != null) {   //处理异常
                                System.out.println("Unexpected error:" + throwable);
                            } else {
                                System.out.println(result);
                            }
                        }
                );
    }

    @Test
    public void test4() throws Exception {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(s -> s + "100")
                .handle((s, t) -> s != null ? Double.parseDouble(s) : 0);

        System.out.println(future.get());
    }

}
