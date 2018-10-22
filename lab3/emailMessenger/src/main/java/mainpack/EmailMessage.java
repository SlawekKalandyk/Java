package mainpack;

import java.util.*;
import java.lang.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailMessage {
    private String from; //required (must be e-mail)
    private String to; //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional

    protected EmailMessage(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.subject = builder.subject;
        this.content = builder.content;
        this.mimeType = builder.mimeType;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
    }

    public void send() {
        final String username = "";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            message.setSubject(this.subject);
            message.setText(this.content);

            Transport.send(message);
            System.out.println("message sent successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private static void checkEmail(String email) {
        for (int i = 0; i < email.length() - 1; ++i) {
            if (email.charAt(i) == '@' || (i == 0 && email.charAt(i) == '@')) {
                return;
            }
        }

        throw new IllegalArgumentException("Email does not have @ sign");
    }

    public static Builder builder() {
        return new EmailMessage.Builder();
    }

    public static class Builder {
        private String from; //required (must be e-mail)
        private String to;
        private String subject; //optional
        private String content; //optional
        private String mimeType;  // optional
        private LinkedList<String> cc; //optional
        private LinkedList<String> bcc; // optional

        public Builder addFrom(String email) {
            checkEmail(email);
            this.from = email;
            return this;
        }

        public Builder addTo(String email) {
            checkEmail(email);
            this.to = email;
            return this;
        }

        public Builder addSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder addContent(String content) {
            this.content = content;
            return this;
        }

        public Builder addMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder addCC(LinkedList<String> cc) {
            for (String email : cc) {
                checkEmail(email);
            }

            this.cc = cc;
            return this;
        }

        public Builder addBCC(LinkedList<String> bcc) {
            for (String email : bcc) {
                checkEmail(email);
            }

            this.bcc = bcc;
            return this;
        }

        public EmailMessage build() {
            return new EmailMessage(this);
        }

    }

}

