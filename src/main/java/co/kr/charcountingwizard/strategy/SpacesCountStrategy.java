package co.kr.charcountingwizard.strategy;

public class SpacesCountStrategy implements CharacterCountStrategy {
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