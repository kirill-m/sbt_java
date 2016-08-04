package ru.sbt.homework5;

/**
 * Created by kirill on 04.08.16
 */
public class StoreException extends RuntimeException {
    public StoreException(String s, Exception e) {
        super(s, e);
    }
}
