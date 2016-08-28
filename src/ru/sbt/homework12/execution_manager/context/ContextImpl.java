package ru.sbt.homework12.execution_manager.context;

import ru.sbt.homework12.execution_manager.ThreadPool;

/**
 * Created by kirill on 28.08.16
 */
public class ContextImpl implements Context {
    private final ThreadPool pool;

    public ContextImpl(ThreadPool pool, Runnable callback, Runnable... tasks) {
        this.pool = pool;
        for (Runnable task : tasks) {
            this.pool.add(task);
        }
        new Thread(() -> {
            this.pool.start();
            while (!this.pool.isFinished()) {}
            new Thread(callback).start();
        }).start();
    }

    @Override
    public int getCompletedTaskCount() {
        return pool.getThreadsFinished();
    }

    @Override
    public int getFailedTaskCount() {
        return pool.getThreadsFailed();
    }

    @Override
    public int getInterruptedTaskCount() {
        return pool.getThreadsInterrupted();
    }

    @Override
    public void interrupt() {
        pool.interrupt();
    }

    @Override
    public boolean isFinished() {
        return pool.isFinished();
    }
}
