package ru.sbt.homework5.part1;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kirill on 04.08.16
 */
public class DataBaseStore implements Store {
    private final Db db;

    public DataBaseStore(Db db) {
        this.db = db;
    }

    @Override
    public void save(String string) {
        try(Db tmp = db) {
            db.insert(string);
        } catch (SQLException e) {
            throw new DbException("SQL error occurred while inserting string " + string, e);
        } catch (Exception e) {
            throw new DbException("Auto-closable exception. ", e);
        }
    }

    @Override
    public List<String> getAll() {
        try(Db tmp = db) {
            return db.selectAll();
        } catch (SQLException e) {
            throw new DbException("SQL error occurred while selecting string ", e);
        } catch (Exception e) {
            throw new DbException("Auto-closable exception. ", e);
        }
    }
}

