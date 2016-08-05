package ru.sbt.homework5.part2.exceptions;

/**
 * Created by kirill on 05.08.16
 */
public class BadMoneyException extends RuntimeException {
    public BadMoneyException(String s, RuntimeException e) {
        super(s, e);
    }
}
