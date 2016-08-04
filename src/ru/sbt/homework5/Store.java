package ru.sbt.homework5;

import java.util.List;

/**
 * Created by kirill on 04.08.16
 */
public interface Store {
    void save(String t);

    List<String> getAll();
}
