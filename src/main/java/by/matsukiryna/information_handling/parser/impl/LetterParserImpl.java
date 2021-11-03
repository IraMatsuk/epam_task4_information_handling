package by.matsukiryna.information_handling.parser.impl;

import by.matsukiryna.information_handling.entity.ComponentType;
import by.matsukiryna.information_handling.entity.SymbolLeaf;
import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.parser.TextParser;

public class LetterParserImpl implements TextParser {
    private static final String LETTER_DELIMITER_REGEX = "";

    @Override
    public TextComposite parse(String wordValue) {
        TextComposite letterComposite = new TextComposite(ComponentType.LETTER);
        String[] symbols = wordValue.split(LETTER_DELIMITER_REGEX);
        for (String symbol : symbols) {
            TextComponent letterComponent = new SymbolLeaf(
                    Character.isLetter(symbol.charAt(0)) ? ComponentType.LETTER : ComponentType.SYMBOL, symbol.charAt(0));
            letterComposite.add(letterComponent);
        }
        return letterComposite;
    }
}
