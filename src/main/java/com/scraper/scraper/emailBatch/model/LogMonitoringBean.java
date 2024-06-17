package com.scraper.scraper.emailBatch.model;

import java.util.List;
import java.util.ArrayList;

public class LogMonitoringBean {

    // Mailing 또는 Report 파일(.txt)
    public String type;

    // 결과 파일(Report) 출력 경로
    public String resultFilePath;

    // 받는 사람 메일 주소
    public String mailReceiver;

    // 파일 경로 (복수 설정 가능)
    public List<String> filePaths = new ArrayList<String>();

    // 키워드 (복수 설정 가능)
    public List<String> keywordList = new ArrayList<String>();

    // 생성자 1
    public LogMonitoringBean() {
    }

    // keywordList.add("error");
    // keywordList.add("exception");
    // filePaths.add("D:/spring-boot/testLog.log");
    // filePaths.add("D:/spring-boot/testLog2.log");

    // 생성자 2
    public LogMonitoringBean(String type, String resultFilePath, String mailReceiver, List<String> filePaths,
            List<String> keywordList) {
        this.type = type;
        this.resultFilePath = resultFilePath;
        this.mailReceiver = mailReceiver;
        this.filePaths = filePaths;
        this.keywordList = keywordList;

    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResultFilePath() {
        return this.resultFilePath;
    }

    public void setResultFilePath(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public String getMailReceiver() {
        return this.mailReceiver;
    }

    public void setMailReceiver(String mailReceiver) {
        this.mailReceiver = mailReceiver;
    }

    public List<String> getFilePaths() {
        return this.filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public List<String> getKeywordList() {
        return this.keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

}
