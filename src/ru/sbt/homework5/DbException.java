package ru.sbt.homework5;

/**
 * Created by kirill on 04.08.16
 */
public class DbException extends StoreException {

    public DbException(String s, Exception e) {
        super(s, e);
    }
}
