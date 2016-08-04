package ru.sbt.homework5;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirill on 04.08.16
 */
public class DbImpl implements Db {
    private List<String> data = new ArrayList<>();

    @Override
    public void insert(String line) throws SQLException {
        data.add(line);
    }

    @Override
    public List<String> selectAll() throws SQLException {
        return data;
    }

    @Override
    public void close() throws Exception {

    }
}
