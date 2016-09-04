package ru.sbt.homework12.execution_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kirill on 28.08.16
 */
public class ThreadPool {
    private static AtomicInteger threadsFinished = new AtomicInteger(0);
    private static AtomicInteger threadsFailed = new AtomicInteger(0);
    private int threadsInterrupted = 0;
    private final List<Thread> pool = new ArrayList<>();

    public void start() {
        pool.forEach(Thread::start);
    }

    public void threadsFinishedInc() {
        threadsFinished.incrementAndGet();
    }

    public void threadsFailedInc() {
        threadsFailed.incrementAndGet();
    }

    public void add(Runnable runnable) {
        pool.add(new Thread(new CallbackRunnable(runnable, this)));
    }

    public boolean isFinished() {
        return threadsFinished.get() + threadsFailed.get() + threadsInterrupted == pool.size();
    }

    public int getThreadsFinished() {
        return threadsFinished.get();
    }

    public int getThreadsFailed() {
        return threadsFailed.get();
    }

    public int getThreadsInterrupted() {
        return threadsInterrupted;
    }

    public synchronized void interrupt() {
        for (Thread thread : pool) {
            if (thread.isAlive()) {
                thread.interrupt();
                threadsInterrupted++;
            }
        }
    }
}
