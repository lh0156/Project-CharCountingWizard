package co.kr.charcountingwizard.service;

import co.kr.charcountingwizard.strategy.*;
import org.springframework.stereotype.Service;

@Service
public class CharacterCountingService {

    public int countCharacters(String text, String option) {
        CharacterCountStrategy strategy = switch (option) {
            case "all" -> new AllCharactersCountStrategy();
            case "english" -> new EnglishCharactersCountStrategy();
            case "korean" -> new KoreanCharactersCountStrategy();
            case "special" -> new SpecialCharactersCountStrategy();
            case "spaces" -> new SpacesCountStrategy();
            default -> throw new IllegalArgumentException("Invalid counting option");
        };

        return strategy.countCharacters(text);
    }
}