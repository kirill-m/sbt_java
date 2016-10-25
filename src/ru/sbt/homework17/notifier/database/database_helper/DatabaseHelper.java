package ru.sbt.homework17.notifier.database.database_helper;

import ru.sbt.homework17.notifier.database.DateRange;

import java.sql.ResultSet;

/**
 * Created by kirill on 17.10.16
 */
public interface DatabaseHelper {
    ResultSet executeQuery(String query, String departmentId, DateRange dateRange);
}
