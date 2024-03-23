package co.kr.charcountingwizard.util;

public class SpacesCountCalculator implements CharacterCountCalculator {
    @Override
    public int countCharacters(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isWhitespace(input.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}