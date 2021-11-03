package by.matsukiryna.information_handling.reader;

import by.matsukiryna.information_handling.exception.CompositeException;

public interface TextReader {
    String readFile(String path) throws CompositeException;
}
