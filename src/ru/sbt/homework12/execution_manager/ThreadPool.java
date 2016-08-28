package ru.sbt.homework12.execution_manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirill on 28.08.16
 */
public class ThreadPool {
    private volatile static int threadsFinished = 0;
    private volatile static int threadsFailed = 0;
    private int threadsInterrupted = 0;
    private final List<Thread> pool = new ArrayList<>();

    public ThreadPool(int maxThreadsInWork) {
    }

    public void start() {
        pool.forEach(Thread::start);
    }

    public static void threadsFinishedInc() {
        threadsFinished++;
    }

    public static void threadsFailedInc() {
        threadsFailed++;
    }

    public void add(Runnable runnable) {
        pool.add(new Thread(new CallbackRunnable(runnable)));
    }

    public boolean isFinished() {
        return threadsFinished + threadsFailed + threadsInterrupted == pool.size();
    }

    public int getThreadsFinished() {
        return threadsFinished;
    }

    public int getThreadsFailed() {
        return threadsFailed;
    }

    public int getThreadsInterrupted() {
        return threadsInterrupted;
    }

    public void interrupt() {
        for (Thread thread : pool) {
            if (thread.isAlive()) {
                thread.interrupt();
                threadsInterrupted++;
            }
        }
    }
}
