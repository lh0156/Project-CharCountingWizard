package co.kr.charcountingwizard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import co.kr.charcountingwizard.config.RateLimiterConfig;
import co.kr.charcountingwizard.service.StatisticsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService textAnalysisService;

    @GetMapping()
    public String showForm(Model model) {
        // 요청 속도 제한을 확인
        if (!RateLimiterConfig.tryConsume()) {
            // 요청 속도가 제한을 초과한 경우 Alert 발생
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "statistics";
        }

        return "statistics";
    }

    @PostMapping()
    public String analyzeText(@RequestParam("text") String text, Model model) {
        // 요청 속도 제한을 확인
        if (!RateLimiterConfig.tryConsume()) {
            // 요청 속도가 제한을 초과한 경우 Alert 발생
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "statistics";
        }

        model.addAttribute("text", text);
        model.addAttribute("analysisResult", textAnalysisService.analyzeText(text));
        return "statistics";
    }
}
