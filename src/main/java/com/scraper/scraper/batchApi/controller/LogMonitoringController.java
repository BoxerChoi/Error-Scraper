package com.scraper.scraper.batchApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scraper.scraper.batchApi.service.LogMonitoringService;

@RestController
@RequestMapping("/monitor")
public class LogMonitoringController {

    public LogMonitoringService logMonitoringService;

    public LogMonitoringController(LogMonitoringService logMonitoringService) {
        this.logMonitoringService = logMonitoringService;
    }

    // 이메일 배치 시작
    @GetMapping("/start")
    public String startLogMonitoring() {
        logMonitoringService.startMonitoring();
        return "Log monitoring started.";
    }

    // 이메일 배치 중단
    @GetMapping("/stop")
    public String stopLogMonitoring() {
        logMonitoringService.stopMonitoring();
        return "Log monitoring stopped.";
    }
}
