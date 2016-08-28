package ru.sbt.homework12.task;

/**
 * Created by kirill on 28.08.16
 */
public class SuddenException extends RuntimeException {
    public SuddenException(String message, Exception exception) {
        super(message, exception);
    }
}
