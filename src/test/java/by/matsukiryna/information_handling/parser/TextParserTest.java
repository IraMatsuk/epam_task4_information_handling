package by.matsukiryna.information_handling.parser;

import by.matsukiryna.information_handling.exception.CompositeException;
import by.matsukiryna.information_handling.parser.impl.ParagraphParserImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextParserTest {
    TextParser textParser;

    @BeforeClass
    public void setUp() {
        textParser = new ParagraphParserImpl();
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testNullDocumentParse() throws CompositeException {
        textParser.parse(null);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testEmptyDocumentParse() throws CompositeException {
        String text = "";
        textParser.parse(text);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testBlankDocumentParse() throws CompositeException {
        String document = "   \n  \t";
        textParser.parse(document);
    }
}
