package ru.sbt.homework17.notifier.mail_sender;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by kirill on 16.09.16
 */
public class MailSender {
    private final String report;
    private final String recipients;
    private final String subject;

    public MailSender(String report, String recipients, String subject) {
        this.report = report;
        this.recipients = recipients;
        this.subject = subject;
    }

    public void send(String host) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = constructMessage(message);
            setMessageParams(helper, subject, true);
        } catch (MessagingException e) {
            throw new RuntimeException("Exception during mail_sender building", e);
        }

        mailSender.send(message);
    }

    private MimeMessageHelper constructMessage(MimeMessage message) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);

        return helper;
    }

    private void setMessageParams(MimeMessageHelper helper, String subject, boolean isHTML) throws MessagingException {
        helper.setText(report, isHTML);
        try {
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
