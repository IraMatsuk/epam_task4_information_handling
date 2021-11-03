package by.matsukiryna.information_handling.parser;

import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.exception.CompositeException;

public interface TextParser {
    TextComposite parse(String textValue) throws CompositeException;
}
