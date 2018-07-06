package aop.timeTaskCancle;

import java.util.Timer;
import java.util.TimerTask;

public class Works extends TimerTask {
    public Timer t;
    public String TName;

    /**
     * 构造方法，获取需要暂停的任务
     *
     * @param t1
     */
    public Works(Timer t1) {
        // TODO Auto-generated constructor stub
        this.t = t1;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("定时任务为：该吃饭了！");
        System.out.println("定时任务即将关闭！");
        can1();
        //can2();
    }

    public void can1() {
        int m = 10;
        while (m > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            TName = Thread.currentThread().getName();
            System.out.println("定时任务仍在继续: " + m + "s" + "    " + "定时任务线程名：" + TName);
            m--;
        }
        t.cancel();
        System.out.println("任务已关闭");
    }


    public void can2() {
        for (int i = 3; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("倒计时: " + i + "s");
        }
        Thread.currentThread().interrupt();
        System.out.println("任务已关闭");
    }

}
