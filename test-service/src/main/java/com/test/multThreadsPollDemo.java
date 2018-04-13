package com.test;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 使用线程池，多线程拆分任务，并轮询获取结果
 */
public class multThreadsPollDemo {

    /**
     * 初始化一个大小为10的线程池
     */
    private static final ExecutorService es = Executors.newFixedThreadPool(10);

    /**
     * 每次任务执行 1000 个
     */
    private static int oneTaskCount = 1000;

    /**
     * 构建需要计算的list
     */
    static Random random = new Random();
    static IntStream intStream = random.ints(0, 100);
    static List<Integer> taskList = intStream.limit(10000).boxed().collect(Collectors.toList());

    /**
     * 任务1：计算传入的list 的和，增加sleep(1) 替代正常业务需要处理的时间
     */
    public static int firstTask(List<Integer> list) throws InterruptedException {
        int sum = 0;
        for(Integer i : list){
            Thread.sleep(1);
            sum += i;
        }
        return sum;
    }

    /**
     * 任务2，休眠1秒，验证异步和同步的计算速度
     */
    public static Integer secondTask() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("任务2完成了");
        return 1;
    }

    /**
     * 多线程分拆计算任务，并且异步处理
     */
    @Test
    public void test1() throws Exception{
        Clock clock = Clock.systemDefaultZone();
        long begin = clock.millis();
        List<List<Integer>> partitions = ListUtils.partition(taskList, oneTaskCount);
        List<Future<Integer>> futures = new ArrayList<>();
        for(int i=0;i<partitions.size();i++){
            List<Integer> task = partitions.get(i);
            Future<Integer> future = es.submit(() -> {
                int sum = firstTask(task);
                return sum;
            });
            futures.add(future);
        }
        List<Integer> sumList = new ArrayList<>();
        //使用轮询，获取future结果
        while (!futures.isEmpty()){
            for(int i = 0;i<futures.size();i++){
                if(futures.get(i).isDone()){
                    sumList.add(futures.get(i).get());
                    futures.remove(i);
                    i--;
                }
            }
        }
        es.shutdown(); //项目接口中不要写此行代码，关闭后，不会再接收新的线程任务
        System.out.println("sumList："+sumList);
        int sum = 0;
        try {
            sum = firstTask(sumList);
            secondTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = clock.millis();
        System.out.println("最终结果："+sum+" 计算间隔："+(end-begin)+"毫秒");
    }

    /**
     *
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException, TimeoutException {
        Clock clock = Clock.systemDefaultZone();
        long begin = clock.millis();
        Future<Integer> submit = es.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = firstTask(taskList);
                return sum;
            }
        });
        Integer sum = 0;
        secondTask();
        boolean flag = true;
        while (flag) {
            if (submit.isDone()){
                // get会阻塞，这里设置一秒超时时间
                sum = submit.get(1,TimeUnit.SECONDS);
                flag = false;
            }
        }
        long end = clock.millis();
        System.out.println("结果是："+sum+" 计算间隔："+(end-begin));
    }


    /**
     *  同步执行 任务1 和 任务2，运行时间是 time1+time2
     */
    @Test
    public void test3() throws InterruptedException {
        Clock clock = Clock.systemDefaultZone();
        long begin = clock.millis();
        int sum = firstTask(taskList);
        //这里 同步执行
        secondTask();
        long end = clock.millis();
        System.out.println("结果是："+sum+" 计算间隔："+(end-begin));
    }

    /**
     * 使用 InvokeAll ，传入多个 callable，一次执行多个任务
     */
    @Test
    public void test4() throws Exception{

        List<Callable<Integer>> callables = Arrays.asList(
                                ()-> firstTask(taskList),
                                ()->secondTask());
        es.invokeAll(callables).stream().map(future ->{
            try {
                System.out.println(future.get());
                return future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }).forEach(System.out::println);
    }

}
