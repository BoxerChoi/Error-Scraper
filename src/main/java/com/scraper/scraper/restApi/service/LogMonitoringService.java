package com.scraper.scraper.restApi.service;

import java.io.File;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class LogMonitoringService {
    @Autowired
    private JavaMailSender mailSender;

    private static final String LOG_FILE_PATH = "D:/spring-boot/testLog.log";
    private static final String ERROR_KEYWORD = "ERROR";
    private static final String EMAIL_TO = "darungchoi@gmail.com";

    @PostConstruct
    public void startMonitoring() {
        TailerListenerAdapter listener = new TailerListenerAdapter() {
            @Override
            public void handle(String line) {
                if (line.contains(ERROR_KEYWORD)) {
                    sendEmailAlert(line);
                }

            }
        };

        Tailer tailer = new Tailer(new File(LOG_FILE_PATH), listener, 1000);
        Thread thread = new Thread(tailer);
        thread.start();
    }

    private void sendEmailAlert(String logMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(EMAIL_TO);
        message.setSubject("Error Alert");
        message.setText("An error occurred:\n\n" + logMessage);
        mailSender.send(message);
    }
}
