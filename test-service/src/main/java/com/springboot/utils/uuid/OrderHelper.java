package com.springboot.utils.uuid;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderHelper {

    /**
     * sn
     */
    private static int sn = 0;

    /**
     * generateId
     *
     * @return ID
     * @throws InterruptedException
     */
    public synchronized static String generateId() throws InterruptedException {
        if (sn == 9) {
            sn = 0;
        } else {
            sn++;
        }
        Thread.sleep(100);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + Integer.toString(sn);
    }
}
