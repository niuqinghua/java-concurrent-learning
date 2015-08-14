package concrrent.learning.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by niuqinghua on 15/8/14.
 */
public class ScheduledExecutorServiceDemoOne {

    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        final Runnable beeper = new Runnable() {
            int count = 0;
            public void run() {
                System.out.println(new Date() + " beep " + (++count));
            }
        };
        // 1秒钟后运行，并每隔2秒运行一次
        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 2, SECONDS);
        // 30秒后结束关闭任务，并且关闭Scheduler
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
                scheduler.shutdown();
            }
        }, 10, SECONDS);
    }

}


