package ru.sbt.homework13.thread_pool;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by kirill on 01.09.16
 */
public class FixedThreadPool implements ThreadPool {
    private final Object lock = new Object();
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final int threadCount;

    public FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            new Worker().start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (lock) {
            tasks.add(runnable);
            lock.notify();
        }
    }

    public class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                synchronized (lock) {
                    while(tasks.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new WorkerException("Worker has been interrupted while waiting", e);
                        }
                    }
                    task = tasks.poll();
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
