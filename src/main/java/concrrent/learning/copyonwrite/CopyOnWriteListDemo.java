package concrrent.learning.copyonwrite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niuqinghua on 15/8/13.
 */
public class CopyOnWriteListDemo {

    /**
     * 读线程
     * @author wangjie
     *
     */
    private static class ReadTask implements Runnable {
        List<String> list;

        public ReadTask(List<String> list) {
            this.list = list;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " read");
            for (String str : list) {
                System.out.println(str);
            }
        }
    }
    /**
     * 写线程
     * @author wangjie
     *
     */
    private static class WriteTask implements Runnable {
        List<String> list;
        int index;

        public WriteTask(List<String> list, int index) {
            this.list = list;
            this.index = index;
        }

        public void run() {
            list.remove(index);
            list.add(index, "write_" + index);
            System.out.println(Thread.currentThread().getName() + " write");
        }
    }

    public void run() {
        final int NUM = 10;
        List<String> list = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < NUM; i++) {
            list.add("main_" + i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(NUM);
        for (int i = 0; i < NUM; i++) {
            executorService.execute(new ReadTask(list));
            executorService.execute(new WriteTask(list, i));
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        new CopyOnWriteListDemo().run();
    }

}
