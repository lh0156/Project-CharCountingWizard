package co.kr.charcountingwizard.controller;

import static co.kr.charcountingwizard.config.RateLimiterConfig.*;

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

    @GetMapping("/countlines")
    public String showLineCountForm(Model model) {
        if (!tryConsume()) {
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "countlines";
        }
        return "countlines";
    }

    @PostMapping("/countlines")
    public String countLines(@RequestParam("text") String text, Model model) {
        if (!tryConsume()) {
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "countlines";
        }
        int lineCount = lineCountingService.countLines(text);
        model.addAttribute("text", text);
        model.addAttribute("lineCount", lineCount);
        return "countlines";
    }
}
