package co.kr.charcountingwizard.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import opennlp.tools.tokenize.SimpleTokenizer;

@Service
public class StatisticsService {

    public String analyzeText(String text) {
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);

        Map<String, Long> wordFrequency = new HashMap<>();
        long specialCharCount = 0;

        for (String token : tokens) {
            // 단어 빈도수 계산
            wordFrequency.merge(token, 1L, Long::sum);
        }

        for (char ch : text.toCharArray()) {
            // 특수 문자 카운트
            if (!Character.isLetterOrDigit(ch)) {
                specialCharCount++;
            }
        }

        long totalChars = text.length();
        double specialCharRatio = (double) specialCharCount / totalChars;

        Map<String, Object> analysisResults = new HashMap<>();
        analysisResults.put("wordFrequency", wordFrequency);
        analysisResults.put("specialCharRatio", specialCharRatio);

        return formatAnalysisResult(analysisResults);
    }

    private String formatAnalysisResult(Map<String, Object> result) {
        StringBuilder builder = new StringBuilder();
        Double specialCharRatio = (Double) result.get("specialCharRatio");
        Map<String, Long> wordFrequency = (Map<String, Long>) result.get("wordFrequency");

        builder.append("특수 문자 비율: ").append(String.format("%.2f%%", specialCharRatio * 100)).append("\n\n");
        builder.append("단어 빈도수\n");
        wordFrequency.forEach((word, freq) -> builder.append(word).append(": ").append(freq).append("\n"));

        return builder.toString();
    }
}