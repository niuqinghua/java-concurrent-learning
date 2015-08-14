package concrrent.learning.threadpool;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by niuqinghua on 15/8/14.
 */
public class ThreadPoolExecutorDemo {

    //private static final int PRODUCE_TASK_SLEEP_TIME = 2;
    private static final int CONSUME_TASK_SLEEP_TIME = 2000;
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE = AVAILABLE_PROCESSORS * 2;
    private static final int PRODUCE_TASK_MAX_NUMBER = MAXIMUM_POOL_SIZE * 20;

    public static void main(String[] args) {
        System.out.println(MAXIMUM_POOL_SIZE);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, MAXIMUM_POOL_SIZE, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue(AVAILABLE_PROCESSORS * 20), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i <= PRODUCE_TASK_MAX_NUMBER; i++) {
            try {
                //产生一个任务，并将其加入到线程池
                String task = "task@ " + i;
                System.out.println("put " + task);
                threadPool.execute(new ThreadPoolTask(task));
                //便于观察，等待一段时间
                //Thread.sleep(PRODUCE_TASK_SLEEP_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程池执行的任务
     *
     * @author hdpan
     */
    public static class ThreadPoolTask implements Runnable, Serializable {
        private static final long serialVersionUID = 0;
        //保存任务所需要的数据
        private Object threadPoolTaskData;

        ThreadPoolTask(Object tasks) {
            this.threadPoolTaskData = tasks;
        }

        public void run() {
            //处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
            System.out.println("start ..." + threadPoolTaskData);
            try {
                ////便于观察，等待一段时间
                Thread.sleep(CONSUME_TASK_SLEEP_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadPoolTaskData = null;
        }

        public Object getTask() {
            return this.threadPoolTaskData;
        }
    }

}
