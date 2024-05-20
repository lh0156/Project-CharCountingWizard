package co.kr.charcountingwizard.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import co.kr.charcountingwizard.config.RateLimiterConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!RateLimiterConfig.tryConsume()) {
            //SC_TOO_MANY_REQUESTS
            response.setStatus(429);
            response.getWriter().write("Too many requests. Please try again later.");
            return false;
        }
        return true;
    }
}
