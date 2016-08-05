package ru.sbt.homework5.part2.exceptions;

/**
 * Created by kirill on 05.08.16
 */
public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String s, RuntimeException e) {
        super(s, e);
    }
}
