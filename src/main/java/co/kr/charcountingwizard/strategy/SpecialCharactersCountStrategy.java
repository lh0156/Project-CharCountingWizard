package co.kr.charcountingwizard.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharactersCountStrategy implements CharacterCountStrategy {

    @Override
    public int countCharacters(String input) {
        Pattern pattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}