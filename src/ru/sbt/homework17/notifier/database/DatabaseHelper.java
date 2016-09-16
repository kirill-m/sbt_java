package ru.sbt.homework17.notifier.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by kirill on 16.09.16
 */
public class DatabaseHelper {
    private final Connection connection;

    public DatabaseHelper(Connection connection) {
        this.connection = connection;
    }

    public ResultSet executeQuery(String departmentId, DateRange dateRange) {
        PreparedStatement ps = getPreparedStatement(DatabaseConst.QUERY);
        injectParams(ps, departmentId, dateRange);
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Could not execute query", e);
        }
    }

    private PreparedStatement getPreparedStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException("Exception happened while preparing query for SQL", e);
        }
    }

    private void injectParams(PreparedStatement ps, String departmentId, DateRange dateRange) {
        LocalDate dateFrom = dateRange.getDateFrom();
        LocalDate dateTo = dateRange.getDateTo();

        try {
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
        } catch (SQLException e) {
            throw new RuntimeException("Exception happened while setting parameters to Prepared Statement", e);
        }
    }

}
