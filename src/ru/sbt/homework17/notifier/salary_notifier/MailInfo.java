package ru.sbt.homework17.notifier.salary_notifier;

import ru.sbt.homework17.notifier.database.DateRange;

/**
 * Created by kirill on 16.09.16
 */
public class MailInfo {
    private final String departmentId;
    private final DateRange range;
    private final String recipients;

    public MailInfo(String departmentId, DateRange range, String recipients, String subject, String host) {
        this.departmentId = departmentId;
        this.range = range;
        this.recipients = recipients;
        this.subject = subject;
        this.host = host;
    }

    private final String subject;
    private final String host;

    public String getHost() {
        return host;
    }

    public String getSubject() {
        return subject;
    }

    public String getRecipients() {
        return recipients;
    }

    public DateRange getRange() {
        return range;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
