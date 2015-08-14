package concrrent.learning.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niuqinghua on 15/8/14.
 */
public class CachedThreadPoolDemo implements Runnable {

    private final int id;

    public CachedThreadPoolDemo(int id) {
        this.id = id;
    }

    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", id " + id);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executeTask(executorService);
        Thread.sleep(999 * 60);
        System.out.println("--------------------");
        executeTask(executorService);
    }

    private static void executeTask(ExecutorService executorService) {
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new CachedThreadPoolDemo(i));
        }
    }

}
