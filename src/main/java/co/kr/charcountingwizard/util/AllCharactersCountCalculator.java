package co.kr.charcountingwizard.util;

public class AllCharactersCountCalculator implements CharacterCountCalculator {
    @Override
    public int countCharacters(String input) {
        return input.length();
    }
}