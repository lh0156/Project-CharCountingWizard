package co.kr.charcountingwizard.controller;

import co.kr.charcountingwizard.config.RateLimiterConfig;
import co.kr.charcountingwizard.service.LineCountingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LineCountingController {

    private final LineCountingService lineCountingService;

    @GetMapping("/count-lines")
    public String showLineCountForm(Model model) {
        // 요청 속도 제한을 확인
        if (!RateLimiterConfig.tryConsume()) {
            // 요청 속도가 제한을 초과한 경우 Alert 발생
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "count-lines";
        }

        return "count-lines";
    }

    @PostMapping("/count-lines")
    public String countLines(@RequestParam("text") String text, Model model) {
        // 요청 속도 제한을 확인
        if (!RateLimiterConfig.tryConsume()) {
            // 요청 속도가 제한을 초과한 경우 Alert 발생
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "count-lines";
        }

        int lineCount = lineCountingService.countLines(text);
        model.addAttribute("text", text);
        model.addAttribute("lineCount", lineCount);
        return "count-lines";
    }
}
