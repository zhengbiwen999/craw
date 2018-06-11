package com.zbw.test;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class java8CompletableFuture3 {

    /**
     * Ether 两个 CompletableFuture 当任意一个计算完成时执行(只能是两个)
     */
    @Test
    public void testEther() throws Exception {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "do future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "do future2";
        });
        CompletableFuture<Void> future = future1.acceptEither(future2, str -> System.out.println("str is " + str));
        CompletableFuture<String> returnFuture = future1.applyToEither(future2, str -> "str is " + str);

        future.get();
        System.out.println(returnFuture.get());
    }

    /**
     * allOf
     * 所有future 完成后返回一个future
     */
    @Test
    public void testAllOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "tony");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "cafei");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "aaron");
        CompletableFuture.allOf(future1, future2, future3).thenApply(v -> Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "))).thenAccept(System.out::print);
    }

    /**
     * anyOf
     * 多个future 任意一个先完成后返回一个future
     */
    @Test
    public void testAnyOf() throws Exception {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "do future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "do future2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "do future3";
        });
        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3);
        System.out.println(future.get());
    }


    /**
     * 异常处理
     * exceptionally
     */
    @Test
    public void testException() {
        CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> {
                    s = null;
                    int length = s.length();
                    return length;
                })
                .thenAccept(i -> System.out.println(i))
                .exceptionally(t -> {
                    System.out.println("Unexpected error:" + t);
                    return null;
                });
    }

}
