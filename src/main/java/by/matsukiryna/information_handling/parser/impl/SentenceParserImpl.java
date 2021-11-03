package by.matsukiryna.information_handling.parser.impl;

import by.matsukiryna.information_handling.entity.ComponentType;
import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.exception.CompositeException;
import by.matsukiryna.information_handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserImpl implements TextParser {
    // u2026 is an ellipsis
    private static final String SENTENCE_DELIMITER_REGEX = ".+?[.?!\\u2026](?=\\s|$)\\p{Punct}";
    private TextParser lexemeParser = new LexemeParserImpl();

    @Override
    public TextComposite parse(String paragraphValue) throws CompositeException {
        if (paragraphValue == null || paragraphValue.isBlank()) {
            throw new CompositeException("Given text is null or blank!");
        }
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        Pattern sentencePattern = Pattern.compile(SENTENCE_DELIMITER_REGEX);
        Matcher sentences = sentencePattern.matcher(paragraphValue);
        while (sentences.find()) {
            TextComponent sentenceComponent = lexemeParser.parse(sentences.group());
            sentenceComposite.add(sentenceComponent);
        }
        return sentenceComposite;
    }
}
