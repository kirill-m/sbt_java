package ru.sbt.homework13.thread_pool;

/**
 * Created by kirill on 01.09.16
 */
public interface ThreadPool {
    void start();

    void execute(Runnable runnable);
}
