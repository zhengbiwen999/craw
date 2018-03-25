package com.springboot.utils.string;

import com.springboot.utils.log.MwLogger;
import org.slf4j.Logger;

/**
 * Created by jeffrey on 10/19/15.
 */
public class IdWorker {
    private final static Logger logger = new MwLogger(IdWorker.class);

    private final int shardingCount = 16;//分片数

    private final long workerId;
    private final long epoch = 1403854494756L;   // 时间起始标记点，作为基准，一般取系统的最近时间
    private final long workerIdBits = 10L;      // 机器标识位数
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 机器ID最大值: 1023
    private long sequence = 0L;                   // 0，并发控制
    private final long sequenceBits = 12L;      //毫秒内自增位

    private final long workerIdShift = this.sequenceBits;                             // 12
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;   // 22
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;                 // 4095,111111111111,12位
    private long lastTimestamp = -1L;

    private IdWorker(long workerId) {
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized String nextId() throws Exception {
        //long timestamp = timeGen();
        long timestamp = System.currentTimeMillis();
        if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            logger.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        long nextId = timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;

        return Long.valueOf(nextId).toString();
    }

    public Long nextId(long bizId, int shardFactor) throws Exception {
        String id = nextId();
        if (shardFactor > 16) {
            new Exception("shardFactor can not larger than 16");
        }
        Long temp = Long.parseLong(id);
        id = Long.toBinaryString(temp) + Long.toBinaryString(bizId % shardFactor);
        System.out.println(id + ",id.length=" + id.length());
        return Long.parseLong(id, 2);
    }

    private static IdWorker flowIdWorker = new IdWorker(1);

    public static IdWorker getFlowIdWorkerInstance() {
        return flowIdWorker;
    }


    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        //long timestamp = timeGen();
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            //timestamp = timeGen();
             timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws Exception {
        IdWorker idWorker = IdWorker.getFlowIdWorkerInstance();

        Long bizId = 12345L;
        int shardCount = 16;

        Long id = idWorker.nextId(bizId, shardCount);

        System.out.println(id);
        System.out.println(id % shardCount);
        System.out.println(bizId % shardCount);

//        long max = 1000000;
//        Set<Long> set = new HashSet<>();
//        System.out.println("开始生成" + max + "个Id...");
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < max; i++) {
//            long nextId = Long.valueOf(idWorker.nextId());
//            set.add(nextId);
////            System.out.println("nextId=" + nextId + ",len=" + (nextId + "").length());
////            String binaryNextId = Long.toBinaryString(nextId);
////            System.out.println("nextId(binary)=" + binaryNextId + ",len=" + (binaryNextId + "").length());
////            System.out.println("\n");
//        }
////        long end = System.currentTimeMillis();
////        System.out.print("生成完毕,共耗时：" + (end - start) + "ms，");
////
////        if (set.size() != max) {
////            System.out.println("有重复!");
////        } else {
////            System.out.println("无重复!");
////        }
////
////        for (Long id : set) {
////            System.out.println(id);
////            break;
////        }
////
////
////        System.out.println(Long.MAX_VALUE + " , " + Long.toBinaryString(Long.MAX_VALUE) + ", len=" + Long.toBinaryString(Long.MAX_VALUE).length());
////        System.out.println(Long.MIN_VALUE + " , " + Long.toBinaryString(Long.MIN_VALUE) + ", len=" + Long.toBinaryString(Long.MIN_VALUE).length());
////
////        String temp = Long.toBinaryString(411750142782215652L) + "0001";
////
////        System.out.println(temp.length());
////
////        System.out.println(Long.parseUnsignedLong(temp, 2));
////        System.out.println(Long.parseLong(temp, 2));
//
////        Set<Long> temp = new HashSet<>();
////        temp.add(1000000L);
////        temp.add(1000000L);
////        System.out.println(temp.size());
    }
}