package by.matsukiryna.information_handling.reader.impl;

import by.matsukiryna.information_handling.exception.CompositeException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TextReaderImplTest {
    private static final String CORRECT_PATH = "testdata/text.txt";
    private static final String INCORRECT_PATH = "test/text.txt";
    TextReaderImpl textReader;

    @BeforeMethod
    public void init() {
        textReader = new TextReaderImpl();

    }

    @Test
    public void testReadFile() throws CompositeException {
        String text = textReader.readFile(CORRECT_PATH);
        Assert.assertFalse(text.isEmpty());
    }

    @Test
    public void testReadTextFromFileWhenPathIsIncorrect() {
        Assert.assertThrows(NullPointerException.class, () -> textReader.readFile(INCORRECT_PATH));
    }
}