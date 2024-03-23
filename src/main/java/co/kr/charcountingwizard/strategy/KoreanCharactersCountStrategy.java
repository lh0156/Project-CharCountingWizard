package co.kr.charcountingwizard.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KoreanCharactersCountStrategy implements CharacterCountStrategy {
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