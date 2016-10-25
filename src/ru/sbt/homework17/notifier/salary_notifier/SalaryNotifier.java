package ru.sbt.homework17.notifier.salary_notifier;

import ru.sbt.homework17.notifier.database.DatabaseConst;
import ru.sbt.homework17.notifier.database.database_helper.DatabaseHelperImpl;
import ru.sbt.homework17.notifier.mail_sender.MailSenderImpl;
import ru.sbt.homework17.notifier.report_builder.HtmlPageBuilder;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by kirill on 16.09.16
 */
public class SalaryNotifier implements Notifier {
    private final MailInfo mailInfo;
    private final Connection connection;

    public SalaryNotifier(MailInfo mailInfo, Connection connection) {
        this.mailInfo = mailInfo;
        this.connection = connection;
    }

    public void notify(boolean isHtml) {
        String report = getReport(getResultSet());
        sendMail(report, isHtml);
    }

    private ResultSet getResultSet() {
        DatabaseHelperImpl helper = new DatabaseHelperImpl(connection);
        return helper.executeQuery(DatabaseConst.QUERY, mailInfo.getDepartmentId(), mailInfo.getRange());
    }

    private String getReport(ResultSet resultSet) {
        HtmlPageBuilder pageBuilder = new HtmlPageBuilder(resultSet);
        return pageBuilder.build();
    }

    private void sendMail(String report, boolean isHtml) {
        MailSenderImpl sender = new MailSenderImpl(report, mailInfo.getRecipients(), mailInfo.getSubject());
        sender.send(mailInfo.getHost(), isHtml);
    }
}
