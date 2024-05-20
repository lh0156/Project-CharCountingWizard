package co.kr.charcountingwizard.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import co.kr.charcountingwizard.config.RateLimiterConfig;
import co.kr.charcountingwizard.exception.RateLimitExceededException;

@Aspect
@Component
public class RateLimitAspect {

    @Before("execution(* co.kr.charcountingwizard.controller..*(..)) && args(model,..)")
    public void checkRateLimit(JoinPoint joinPoint, Model model) throws RateLimitExceededException {
        if (!RateLimiterConfig.tryConsume()) {
            model.addAttribute("error", "Too many requests. Please try again later.");
            throw new RateLimitExceededException("Too many requests. Please try again later.");
        }
    }
}
