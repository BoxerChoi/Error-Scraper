package com.scraper.scraper.emailBatch.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scraper.scraper.emailBatch.model.LogMonitoringBean;
import com.scraper.scraper.emailBatch.service.LogMonitoringService;

import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;

// @RestController
@Controller
@RequestMapping("/monitor")
public class LogMonitoringController {

    public LogMonitoringService logMonitoringService;

    public LogMonitoringController(LogMonitoringService logMonitoringService) {
        this.logMonitoringService = logMonitoringService;
    }

    // 입력 값 세팅
    @GetMapping("/settings")
    public String showSettings(Model model) {
        model.addAttribute("logMonitoringBean", new LogMonitoringBean());
        return "settings"; // settings.html을 반환
    }

    // 이메일 배치 시작
    @GetMapping("/start")
    public String startLogMonitoring(@ModelAttribute LogMonitoringBean logMonitoringBean) {
        logMonitoringService.startMonitoring(logMonitoringBean);
        return "redirect:/monitor/success";
    }

    // 이메일 배치 중단
    @GetMapping("/stop")
    public String stopLogMonitoring() {
        logMonitoringService.stopMonitoring();
        return "redirect:/monitor/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}
