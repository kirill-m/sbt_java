package ru.sbt.homework17.notifier.database;

import java.time.LocalDate;

/**
 * Created by kirill on 16.09.16
 */
public class DateRange {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public DateRange(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
