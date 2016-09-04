package ru.sbt.homework12.execution_manager;

/**
 * Created by kirill on 28.08.16
 */
public class CallbackRunnable implements Runnable {
    private final Runnable runnable;
    private final ThreadPool pool;

    CallbackRunnable(Runnable runnable, ThreadPool pool) {
        this.runnable = runnable;
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            runnable.run();

            pool.threadsFinishedInc();
        } catch (RuntimeException e) {
            pool.threadsFailedInc();
        }

    }
}
