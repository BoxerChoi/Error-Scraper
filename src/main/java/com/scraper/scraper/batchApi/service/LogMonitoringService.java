package com.scraper.scraper.batchApi.service;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// import jakarta.annotation.PostConstruct;

@Service
public class LogMonitoringService {

    private static final Logger logger = LogManager.getLogger(LogMonitoringService.class);

    @Autowired
    private JavaMailSender mailSender;

    // 멀티 스레드 환경에서는 스레드 간 동기화 문제가 발생할 수 있으므로 큐와 ConcurrentLinkedQueue 사용
    private final Queue<String> QueueFilePaths = new ConcurrentLinkedQueue<>();
    private final Queue<Tailer> tailers = new ConcurrentLinkedQueue<>();
    private final Queue<Thread> threads = new ConcurrentLinkedQueue<>();

    public String email_to = "darungchoi@gmail.com";
    public List<String> filePaths = new ArrayList<String>();
    public List<String> keywordList = new ArrayList<String>();

    // @PostConstruct
    public void startMonitoring() {
        logger.info("#startMonitoring");
        keywordList.add("error");
        keywordList.add("exception");
        filePaths.add("D:/spring-boot/testLog.log");
        filePaths.add("D:/spring-boot/testLog2.log");

        // Queue에 다중 파일경로 추가
        for (String filePath : filePaths) {
            QueueFilePaths.offer(filePath);
        }

        while (!QueueFilePaths.isEmpty()) {
            String QueueFilePath = QueueFilePaths.poll();
            TailerListenerAdapter listener = new TailerListenerAdapter() {
                @Override
                public void handle(String line) {
                    String lowerCaseLine = line.toLowerCase(); // 모든 문자열을 소문자로 변환
                    for (String keyword : keywordList) {
                        if (lowerCaseLine.contains(keyword)) {
                            sendEmailAlert(line);
                            break; // 하나의 키워드만 일치하면 이메일을 보내고 종료
                        }
                    }
                }
            };

            Tailer tailer = new Tailer(new File(QueueFilePath), listener, 1000, true); // end:true 설정 시 파일 끝단에서 시작
            Thread thread = new Thread(tailer);
            thread.start();

            tailers.offer(tailer);
            threads.offer(thread);

        }
    }

    public void stopMonitoring() {

        for (Tailer tailer : tailers) {
            tailer.stop();
        }
        for (Thread thread : threads) {
            thread.interrupt();
        }

        threads.clear();
        tailers.clear();

    }

    private void sendEmailAlert(String logMessage) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email_to);
            message.setSubject("Error Alert");
            message.setText("An error occurred:\n\n" + logMessage);
            mailSender.send(message);

            logger.info("Email sent for log line: {}", message);

        } catch (Exception e) {
            logger.error("MessagingException occured(sendEmailAlert) : " + e);
        }

    }
}
