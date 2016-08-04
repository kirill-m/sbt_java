package ru.sbt.homework5.part1;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kirill on 04.08.16
 */
public interface Db extends AutoCloseable {
    void insert(String line) throws SQLException;

    List<String> selectAll() throws SQLException;
}
