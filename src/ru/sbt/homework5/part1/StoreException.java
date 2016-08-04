package ru.sbt.homework5.part1;

/**
 * Created by kirill on 04.08.16
 */
public class StoreException extends RuntimeException {
    public StoreException(String s, Exception e) {
        super(s, e);
    }
}
