package ru.sbt.homework5.part2.exceptions;

/**
 * Created by kirill on 05.08.16
 */
public class AttemptsLimitException extends RuntimeException {
    public AttemptsLimitException(String s, RuntimeException e) {
        super(s, e);
    }
}
