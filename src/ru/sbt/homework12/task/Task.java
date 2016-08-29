package ru.sbt.homework12.task;

import java.util.concurrent.Callable;

/**
 * Created by kirill on 27.08.16
 */
public class Task<T> {
    private final Object lock = new Object();
    private final Callable<? extends T> callable;
    private volatile T cache = null;
    private volatile Exception exception = null;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (cache != null) {
            return cache;
        }
        synchronized (lock) {
            if (exception != null)
                throw new SuddenException("An exception has been thrown", exception);

            T result = null;
            try {
                result = callable.call();
                cache = result;
                return result;
            } catch (Exception e) {
                exception = e;
                throw new RuntimeException("Unable to compute a result", e);
            }
        }
    }
}

