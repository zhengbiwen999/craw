package com.springboot.utils.uuid;

import java.util.UUID;


public class UUIDUtil {

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static String getTimeBasedUuid() {
        UUID uuid = org.apache.logging.log4j.core.util.UuidUtil.getTimeBasedUuid();
        return uuid.toString().replaceAll("-", "");
    }

    public static String getUuid_(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getTimeBasedUuid_() {
        UUID uuid = org.apache.logging.log4j.core.util.UuidUtil.getTimeBasedUuid();
        return uuid.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(getUuid());
        }
        System.out.println("-----------------");
        for (int i = 0; i < 10; i++) {
//            Thread.sleep(1);
            System.out.println(getTimeBasedUuid());
        }
    }

}

