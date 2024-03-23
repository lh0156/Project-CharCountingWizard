package co.kr.charcountingwizard.service;

import co.kr.charcountingwizard.util.AllCharactersCountCalculator;
import co.kr.charcountingwizard.util.CharacterCountCalculator;
import co.kr.charcountingwizard.util.EnglishCharactersCountCalculator;
import co.kr.charcountingwizard.util.KoreanCharactersCountCalculator;
import co.kr.charcountingwizard.util.SpacesCountCalculator;
import co.kr.charcountingwizard.util.SpecialCharactersCountCalculator;

import org.springframework.stereotype.Service;

@Service
public class CharacterCountingService {

    public int countCharacters(String text, String option) {
        CharacterCountCalculator strategy = switch (option) {
            case "all" -> new AllCharactersCountCalculator();
            case "english" -> new EnglishCharactersCountCalculator();
            case "korean" -> new KoreanCharactersCountCalculator();
            case "special" -> new SpecialCharactersCountCalculator();
            case "spaces" -> new SpacesCountCalculator();
            default -> throw new IllegalArgumentException("Invalid counting option");
        };

        return strategy.countCharacters(text);
    }
}