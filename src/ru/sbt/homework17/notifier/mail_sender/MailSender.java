package ru.sbt.homework17.notifier.mail_sender;

/**
 * Created by kirill on 17.10.16
 */
public interface MailSender {
    public void send(String host, boolean isHtml);
}
