package ru.sbt.homework5.part2;

/**
 * Created by kirill on 04.08.16.
 */
public class NotAllowedOperationException extends RuntimeException {


    public NotAllowedOperationException(String s, RuntimeException e) {
        super(s, e);
    }
}
