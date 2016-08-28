package ru.sbt.homework12.execution_manager;

/**
 * Created by kirill on 28.08.16
 */
public class CallbackRunnable implements Runnable {
    private final Runnable runnable;

    CallbackRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            runnable.run();
            ThreadPool.threadsFinishedInc();
        } catch (RuntimeException e) {
            ThreadPool.threadsFailedInc();
        }

    }
}
