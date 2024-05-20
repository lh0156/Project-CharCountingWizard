package co.kr.charcountingwizard.service;

import org.springframework.stereotype.Service;

@Service
public class LineCountingService {

    public int countLines(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return text.split("\r?\n").length;
    }
}
