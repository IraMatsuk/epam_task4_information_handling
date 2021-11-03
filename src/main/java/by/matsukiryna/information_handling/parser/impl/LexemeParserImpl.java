package by.matsukiryna.information_handling.parser.impl;

import by.matsukiryna.information_handling.entity.ComponentType;
import by.matsukiryna.information_handling.entity.SymbolLeaf;
import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParserImpl implements TextParser {
    private static final String LEXEME_DELIMITER_REGEX = "\\S+";
    private static final String WORD_DELIMITER_REGEX = "[а-яА-Я\\w]+";
    private final TextParser wordParser = new WordParserImpl();
    private final TextParser expressionParser = new ExpressionParserImpl();


    @Override
    public TextComposite parse(String sentenceValue) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        Pattern lexemePattern = Pattern.compile(LEXEME_DELIMITER_REGEX);
        Matcher lexemes = lexemePattern.matcher(sentenceValue);
        while (lexemes.find()) {
            String lexeme = lexemes.group();
            if (lexeme.matches(WORD_DELIMITER_REGEX)) {
                TextComponent wordComponent = wordParser.parse(lexeme);
                lexemeComposite.add(wordComponent);
            } else {
                String possibleWord = lexeme.substring(0, lexeme.length() - 1);
                if (possibleWord.matches(WORD_DELIMITER_REGEX)) {
                    TextComponent wordComponent = wordParser.parse(possibleWord);
                    lexemeComposite.add(wordComponent);
                    lexemeComposite.add(new SymbolLeaf(ComponentType.SYMBOL, lexeme.charAt(possibleWord.length())));
                } else {
                    TextComponent expressionComponent = expressionParser.parse(lexeme);
                    lexemeComposite.add(expressionComponent);
                }
            }
        }
        return lexemeComposite;
    }
}
