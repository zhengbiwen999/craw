package aop.timeTaskCancle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class CancleTask {

    public static void main(String[] args) throws InterruptedException {
        works();
    }

    /**
     * 该方法验证的是有参构造方法的Works
     * 使用定时器取消任务
     * 定时器对象如何被继承TimerTask的类获取
     *
     * @throws InterruptedException
     */
    public static void works() throws InterruptedException {
        Timer t1 = new Timer();
        Works w = new Works(t1);
        t1.schedule(w, 6000);
        for (int i = 5; i > 0; i--) {
            Thread.sleep(1000);
            System.out.println("任务还有" + i + "s" + "开启" + "     " + "主线程线程名为：" + Thread.currentThread().getName());
        }
        while (true) {
            Date date = new Date();
            SimpleDateFormat da = new SimpleDateFormat("HH:mm:ss");
            Thread.sleep(1000);
            System.out.println(da.format(date) + "    " + Thread.currentThread().getName());
        }
    }
}
