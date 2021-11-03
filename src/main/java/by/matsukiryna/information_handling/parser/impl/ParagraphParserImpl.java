package by.matsukiryna.information_handling.parser.impl;

import by.matsukiryna.information_handling.entity.ComponentType;
import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.parser.TextParser;

public class ParagraphParserImpl implements TextParser {
    private static final String PARAGRAPH_DELIMITER_REGEX = "\\r\\n";
    private final TextParser sentenceParser = new SentenceParserImpl();

    @Override
    public TextComposite parse(String textValue) {
        String[] paragraphs = textValue.split(PARAGRAPH_DELIMITER_REGEX);
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parse(paragraph);
            paragraphComposite.add(paragraphComponent);
        }
        return paragraphComposite;
    }
}
