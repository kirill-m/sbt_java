package ru.sbt.homework17.notifier.salary_notifier;

import ru.sbt.homework17.notifier.database.DatabaseHelper;
import ru.sbt.homework17.notifier.mail_sender.MailSender;
import ru.sbt.homework17.notifier.report_builder.HtmlPageBuilder;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by kirill on 16.09.16
 */
public class Notifier {
    private final MailInfo mailInfo;
    private final Connection connection;

    public Notifier(MailInfo mailInfo, Connection connection) {
        this.mailInfo = mailInfo;
        this.connection = connection;
    }

    public void notifyAboutSalary() {
        String report = getReport(getResultSet());
        sendMail(report);
    }

    private ResultSet getResultSet() {
        DatabaseHelper helper = new DatabaseHelper(connection);
        return helper.executeQuery(mailInfo.getDepartmentId(), mailInfo.getRange());
    }

    private String getReport(ResultSet resultSet) {
        HtmlPageBuilder pageBuilder =  new HtmlPageBuilder(resultSet);
        return pageBuilder.build();
    }

    private void sendMail(String report) {
        MailSender sender =  new MailSender(report, mailInfo.getRecipients(), mailInfo.getSubject());
        sender.send(mailInfo.getHost());
    }
}
