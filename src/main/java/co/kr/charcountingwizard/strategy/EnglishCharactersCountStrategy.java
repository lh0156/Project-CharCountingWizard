package co.kr.charcountingwizard.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnglishCharactersCountStrategy implements CharacterCountStrategy {
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