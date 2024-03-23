package co.kr.charcountingwizard.strategy;

public class AllCharactersCountStrategy implements CharacterCountStrategy {
    @Override
    public int countCharacters(String input) {
        return input.length();
    }
}