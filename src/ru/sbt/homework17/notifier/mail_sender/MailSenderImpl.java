package ru.sbt.homework17.notifier.mail_sender;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by kirill on 16.09.16
 */
public class MailSenderImpl implements MailSender {
    private final String report;
    private final String recipients;
    private final String subject;

    public MailSenderImpl(String report, String recipients, String subject) {
        this.report = report;
        this.recipients = recipients;
        this.subject = subject;
    }

    public void send(String host, boolean isHtml) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail_sender.google.com");
        MimeMessage message = getMimeMessage(mailSender, isHtml);
        mailSender.send(message);
    }

    private MimeMessage getMimeMessage(JavaMailSenderImpl mailSender, boolean isHtml) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = constructMessage(message);
            setMessageParams(helper, subject, isHtml);
        } catch (MessagingException e) {
            throw new RuntimeException("Exception during mail_sender building", e);
        }
        return message;
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
            throw new RuntimeException("Exception while setting message params", e);
        }
    }

}
