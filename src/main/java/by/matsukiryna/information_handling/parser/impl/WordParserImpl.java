package by.matsukiryna.information_handling.parser.impl;

import by.matsukiryna.information_handling.entity.ComponentType;
import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.parser.TextParser;

public class WordParserImpl implements TextParser {
    private final TextParser letterParser = new LetterParserImpl();

    @Override
    public TextComposite parse(String lexemeValue) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        TextComponent wordComponent = letterParser.parse(lexemeValue);
        wordComposite.add(wordComponent);
        return wordComposite;
    }
}
