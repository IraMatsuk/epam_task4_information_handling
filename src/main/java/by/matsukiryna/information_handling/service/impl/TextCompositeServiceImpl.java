package by.matsukiryna.information_handling.service.impl;

import by.matsukiryna.information_handling.entity.ComponentType;
import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.exception.CompositeException;
import by.matsukiryna.information_handling.parser.TextParser;
import by.matsukiryna.information_handling.parser.impl.LexemeParserImpl;
import by.matsukiryna.information_handling.parser.impl.WordParserImpl;
import by.matsukiryna.information_handling.service.TextCompositeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class TextCompositeServiceImpl implements TextCompositeService {
    private static final Logger logger = LogManager.getLogger();
    private static final String VOWELS_REGEX = "^(?ui:[aeiouyаеёийоуэюя]).*"; // include UNICODE_CASE (?ui:...)
    private static final String CONSONANT_REGEX = "^(?ui:[^aeiouyаеёийоуэюя]).*";

    @Override
    public List<TextComponent> sortParagraph(TextComposite text) {
        List<TextComponent> paragraphs = text.getChild();
        paragraphs.sort(new TextComposite.SentenceAmountComparator());
        return paragraphs;
    }

    @Override
    public TextComponent findSentenceWithLongestWord(String text) {
        return null;
    }

    @Override
    public String deleteAmountSameWords(String text, int amount) {
        return null;
    }

    @Override
    public Map<String, Long> findEqualWords(TextComposite textComposite) {
        Map<String, Long> equalWords = textComposite.getChild()
                                                    .stream()
                                                    .flatMap(p -> p.getChild().stream())
                                                    .collect(Collectors.toList())
                                                    .stream()
                                                    .flatMap(s -> s.getChild().stream())
                                                    .collect(Collectors.toList())
                                                    .stream()
                                                    .filter(l -> l.getType().equals(ComponentType.WORD))
                                                    .collect(Collectors.groupingBy(w -> w.toString().toLowerCase(), Collectors.counting()));
        equalWords.entrySet().removeIf(w -> w.getValue() == 1);
        return equalWords;
    }

    @Override
    public Map<String, Integer> findEqual(TextComposite composite) {
        Map<String, Integer> equal = Arrays.asList(composite.toString()).parallelStream()
                                            .collect(Collectors.toConcurrentMap(w -> w, w -> 1, Integer::sum));
        return equal;
    }

    @Override
    public long countVowels(String text) {
        return countSymbols(text, VOWELS_REGEX);
    }

    @Override
    public long countConsonant(String text) {
        return countSymbols(text, CONSONANT_REGEX);
    }

    private long countSymbols(String text, String regex) {
        WordParserImpl wordParser = new WordParserImpl();
        TextComponent textComponent = wordParser.parse(text);
        List<TextComponent> symbols = textComponent.getChild();
        long vowelAmount = symbols.stream()
                .filter(o -> o.toString().matches(regex))
                .count();
        return vowelAmount;
    }
}
