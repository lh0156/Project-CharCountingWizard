package co.kr.charcountingwizard.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KoreanCharactersCountCalculator implements CharacterCountCalculator {
    @Override
    public int countCharacters(String input) {
        Pattern pattern = Pattern.compile("[가-힣]");
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}