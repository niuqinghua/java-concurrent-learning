package concrrent.learning.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niuqinghua on 15/8/14.
 */
public class FixedThreadPoolDemo implements Runnable {

    private int index;

    public FixedThreadPoolDemo(int i) {
        this.index = i;
    }

    public void run() {
        try {
            System.out.println("[" + this.index + "] start....");
            Thread.sleep((int) (Math.random() * 10000));
            System.out.println("[" + this.index + "] end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            service.execute(new FixedThreadPoolDemo(i));
        }
        System.out.println("submit finish");
        service.shutdown();
    }
}