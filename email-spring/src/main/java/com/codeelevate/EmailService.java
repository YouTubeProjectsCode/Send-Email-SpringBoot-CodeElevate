package com.codeelevate;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostConstruct
    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("[your email where you send from]");
        mimeMessageHelper.setTo("[your email where you send to]");
        mimeMessageHelper.setText("Hello, This is body");
        mimeMessageHelper.setSubject("This is a subject");

        FileSystemResource fileSystemResource = new FileSystemResource(new File("C:\\Users\\Advance\\Downloads\\capture.png"));

        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),fileSystemResource);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail sent to adarax.studio@gmail.com");
    }

}
