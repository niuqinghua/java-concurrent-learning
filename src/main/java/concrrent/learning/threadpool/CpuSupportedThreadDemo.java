package concrrent.learning.threadpool;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by niuqinghua on 15/8/28.
 */
public class CpuSupportedThreadDemo implements Callable<Long> {

    public static final int N_THREADS = 1500;
    public static final List<Long> timestamp = new ArrayList<Long>();

    public Long call() throws Exception {
        return System.currentTimeMillis();
    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        timestamp.add(System.currentTimeMillis());
        ExecutorService service = Executors.newFixedThreadPool(N_THREADS);
        System.out.println(System.currentTimeMillis());
        List<Future<Long>> futures = new ArrayList<Future<Long>>();
        for (int i = 0; i < N_THREADS; i++) {
            futures.add(service.submit(new CpuSupportedThreadDemo()));
        }
        service.shutdown();
        for (Future<Long> future : futures) {
            timestamp.add(future.get());
        }
        Collections.sort(timestamp);
        System.out.println(timestamp.get(timestamp.size() - 1) - timestamp.get(0));
    }

}
