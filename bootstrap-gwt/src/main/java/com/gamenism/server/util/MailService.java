package com.gamenism.server.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
    private static final Logger logger = Logger.getLogger(MailService.class.getName());

    private Properties properties;
    private Session session;

    private static MailService instance;

    public static MailService getInstance() {
        if (instance == null) {
            instance = new MailService();
            instance.init();
        }
        return instance;
    }

    private void init() {
        properties = new Properties();
        session = Session.getDefaultInstance(properties, null);
    }

    public boolean send(String senderAddress, String senderName,
                        String recipientAddress, String recipientName,
                        String body, String subject) {

        try {
            InternetAddress sender = new InternetAddress(senderAddress, senderName);
            InternetAddress recipient = new InternetAddress(recipientAddress, recipientName);

            Message message = new MimeMessage(session);
            message.setFrom(sender);
            message.addRecipient(Message.RecipientType.TO, recipient);
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        }
        catch (UnsupportedEncodingException uee) {
            logger.log(Level.WARNING, "Unsupported encoding failure", uee);
            return false;
        }
        catch (AddressException ae) {
            logger.log(Level.WARNING, "Invalid address", ae);
            return false;
        }
        catch (MessagingException me) {
            logger.log(Level.WARNING, "Message failed", me);
            return false;
        }
        logger.log(Level.FINEST, "Message successfully sent to " + senderAddress);

        return true;
    }

}