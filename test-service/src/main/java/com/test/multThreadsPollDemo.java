package com.test;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 使用线程池，多线程拆分任务，并轮询获取结果
 */
public class multThreadsPollDemo {

    private static final ExecutorService es = Executors.newFixedThreadPool(10);

    private static int oneTaskCount = 1000;

    public static int getListSum(List<Integer> list) throws InterruptedException {
        int sum = 0;
        for(Integer i : list){
            Thread.sleep(1);
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception{
        Clock clock = Clock.systemDefaultZone();
        long begin = clock.millis();

        Random random = new Random();
        IntStream intStream = random.ints(0, 100);
       //生成需要计算的大集合
        List<Integer> taskList = intStream.limit(10000).boxed().collect(Collectors.toList());
        List<List<Integer>> partitions = ListUtils.partition(taskList, oneTaskCount);

        List<Future<Integer>> futures = new ArrayList<>();
        for(int i=0;i<partitions.size();i++){
            List<Integer> task = partitions.get(i);
            Future<Integer> future = es.submit(() -> {
                int sum = getListSum(task);
                return sum;
            });
            futures.add(future);
        }
        List<Integer> sumList = new ArrayList<>();
        while (!futures.isEmpty()){
            for(int i = 0;i<futures.size();i++){
                if(futures.get(i).isDone()){
                    sumList.add(futures.get(i).get());
                    futures.remove(i);
                    i--;
                }
            }
        }
        System.out.println("sumList："+sumList);
        int sum = getListSum(sumList);
        long end = clock.millis();
        System.out.println("最终结果："+sum+" 计算间隔："+(end-begin)+"毫秒");
    }



}
