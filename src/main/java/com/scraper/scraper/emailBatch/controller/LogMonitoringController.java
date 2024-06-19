package com.scraper.scraper.emailBatch.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scraper.scraper.emailBatch.model.LogMonitoringBean;
import com.scraper.scraper.emailBatch.service.LogMonitoringService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/monitor")
public class LogMonitoringController {

    public LogMonitoringService logMonitoringService;
    private LogMonitoringBean monitorBean = new LogMonitoringBean();

    public LogMonitoringController(LogMonitoringService logMonitoringService) {
        this.logMonitoringService = logMonitoringService;
    }

    // 입력 값 세팅
    @GetMapping("/settings")
    public String showSettings(Model model) {
        model.addAttribute("activeThreadCount", monitorBean.getActiveThreadCount());
        return "settings"; // settings.html을 반환
    }

    // 이메일 배치 시작
    @PostMapping("/start")
    public String startLogMonitoring(@ModelAttribute LogMonitoringBean logMonitoringBean) {

        // 파라미터 검증
        if (logMonitoringBean.getFilePaths().size() <= 0 || logMonitoringBean.getKeywordList().size() <= 0
                || "".equals(logMonitoringBean.mailReceiver)) {

            monitorBean.setParamFlag("N");
            return "redirect:/monitor/failed";

        } else { // 배치 성공 여부 검증
            monitorBean = logMonitoringService.startMonitoring(logMonitoringBean);

            if ("Y".equals(monitorBean.getFailYn())) {
                return "redirect:/monitor/failed";
            }
        }

        return "redirect:/monitor/success";
    }

    // 이메일 배치 중단
    @PostMapping("/stop")
    public String stopLogMonitoring() {
        monitorBean = logMonitoringService.stopMonitoring();
        return "redirect:/monitor/success";
    }

    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        model.addAttribute("activeThreadCount", monitorBean.getActiveThreadCount());
        return "success";
    }

    @GetMapping("/failed")
    public String showFailedPage(Model model) {

        // 파라미터 누락
        if ("N".equals(monitorBean.getParamFlag())) {
            model.addAttribute("reason", "missing parameters.");
        }

        // 배치 등록 실패
        if ("Y".equals(monitorBean.getParamFlag()) && "Y".equals(monitorBean.getFailYn())) {
            model.addAttribute("reason", "program error.");
        }

        model.addAttribute("activeThreadCount", monitorBean.getActiveThreadCount());
        return "failed";
    }
}
