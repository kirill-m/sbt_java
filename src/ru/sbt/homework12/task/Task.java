package ru.sbt.homework12.task;

import java.util.concurrent.Callable;

/**
 * Created by kirill on 27.08.16
 */
public class Task<T> {
    private final Object lock = new Object();
    private final Callable<? extends T> callable;
    private volatile T cache = null;
    volatile boolean finished = false;
    private SuddenException exception = null;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (!finished) {
            synchronized (lock) {
                if (!finished) {
                    try {
                        cache = callable.call();
                    } catch (Exception e) {
                        exception = new SuddenException("An exception has been thrown", exception);
                        throw exception;
                    } finally {
                        finished = true;
                    }
                }
            }
        }

        if (exception != null)
            throw exception;

        return cache;
    }
}

