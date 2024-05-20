package co.kr.charcountingwizard.controller;

import co.kr.charcountingwizard.config.RateLimiterConfig;
import co.kr.charcountingwizard.service.CharacterCountingService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CharacterCountingController {

    private final CharacterCountingService characterCountingService;

    @GetMapping("/")
    public String showForm(Model model) {
        // 요청 속도 제한을 확인
        if (!RateLimiterConfig.tryConsume()) {
            // 요청 속도가 제한을 초과한 경우 Alert 발생
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "index";
        }

        return "index";
    }

    @PostMapping("/")
    public String countCharacters(@RequestParam("text") String text,
                                  @RequestParam("option") String option,
                                  Model model) {

        // 요청 속도 제한을 확인
        if (!RateLimiterConfig.tryConsume()) {
            // 요청 속도가 제한을 초과한 경우 Alert 발생
            model.addAttribute("error", "Too many requests. Please try again later.");
            return "index";
        }

        int count = characterCountingService.countCharacters(text, option);
        model.addAttribute("text", text);
        model.addAttribute("option", option);
        model.addAttribute("count", count);
        return "index";
    }
}
