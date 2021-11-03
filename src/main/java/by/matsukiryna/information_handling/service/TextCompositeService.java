package by.matsukiryna.information_handling.service;

import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.exception.CompositeException;

import java.util.List;
import java.util.Map;

public interface TextCompositeService {
    List<TextComponent> sortParagraph(TextComposite text);

    TextComponent findSentenceWithLongestWord(String text);

    String deleteAmountSameWords(String text, int amount);

    Map<String, Long> findEqualWords(TextComposite textComposite);

    Map<String, Integer> findEqual(TextComposite composite);

    long countVowels(String text);

    long countConsonant(String text);
}
