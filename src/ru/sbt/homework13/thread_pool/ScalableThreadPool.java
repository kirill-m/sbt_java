package ru.sbt.homework13.thread_pool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kirill on 02.09.16
 */
public class ScalableThreadPool implements ThreadPool {
    private final Object lock = new Object();
    private final int minCount;
    private final int maxCount;
    private int currentSleepingCount;
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final List<Worker> pool = new ArrayList<>();

    public ScalableThreadPool(int minCount, int maxCount) {
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public void start() {
        for (int i = 0; i < minCount; i++) {
            pool.add(new Worker());
        }

        pool.forEach(Worker::start);
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);

        if (currentSleepingCount == 0 && pool.size() >= minCount && pool.size() < maxCount) {
            pool.add(new Worker());
            pool.get(pool.size() - 1).start();
        }

        synchronized (lock) {
            lock.notify();
        }

    }

    private void currentSleepingCountInc() {
        synchronized (lock) {
            currentSleepingCount++;
            if (currentSleepingCount >= maxCount) {
                for (int i = minCount; i < maxCount; i++) {
                    pool.get(minCount - 1).interrupt();
                    pool.remove(minCount - 1);
                }
                currentSleepingCount = minCount;
            }
        }
    }

    private void currentSleepingCountDec() {
        synchronized (lock) {
            currentSleepingCount--;
        }
    }

    /*
    *  Debugging helper method
    * */
    private void printAll() {
        System.out.println("Current sleeping count: " + currentSleepingCount);
        System.out.println("Pool size: " + pool.size());
        System.out.println("Tasks size: " + tasks.size());
        System.out.println("--------------------------");
    }

    public class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task = tasks.poll();
                while (task == null) {
                    synchronized (lock) {
                        try {
                            currentSleepingCountInc();
                            lock.wait();
                            task = tasks.poll();
                            currentSleepingCountDec();
                        } catch (InterruptedException e) {
                            Logger.getLogger("hw13.thread_pool")
                                    .log(Level.INFO, "Worker has been interrupted while waiting", e);
                        }
                    }
                }
                try {
                    task.run();
                } catch (Exception e) {
                    throw new WorkerException("Exception occurred during worker running", e);
                }
            }
        }
    }
}
