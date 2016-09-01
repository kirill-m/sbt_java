package ru.sbt.homework13.thread_pool;

/**
 * Created by kirill on 01.09.16
 */
public class WorkerException extends RuntimeException {
    public WorkerException(String message, Exception e) {
        super(message, e);
    }
}
