package co.kr.charcountingwizard.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnglishCharactersCountCalculator implements CharacterCountCalculator {
    @Override
    public int countCharacters(String input) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}