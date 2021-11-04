package by.matsukiryna.information_handling.main;

import by.matsukiryna.information_handling.entity.TextComponent;
import by.matsukiryna.information_handling.entity.TextComposite;
import by.matsukiryna.information_handling.exception.CompositeException;
import by.matsukiryna.information_handling.parser.TextParser;
import by.matsukiryna.information_handling.parser.impl.*;
import by.matsukiryna.information_handling.reader.impl.TextReaderImpl;
import by.matsukiryna.information_handling.service.TextCompositeService;
import by.matsukiryna.information_handling.service.impl.TextCompositeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String  PATH_TO_FILE = "data/text.txt";

    public static void main(String[] args) throws CompositeException {

        TextReaderImpl reader = new TextReaderImpl();
        String text = reader.readFile(PATH_TO_FILE);

        TextParser parser = new ParagraphParserImpl();
        TextComposite textComponent = parser.parse(text);

        TextComponent sentences = textComponent.getChild().get(0).getChild().get(0);

        TextCompositeService service = new TextCompositeServiceImpl();
        service.countVowels(sentences.toString());
        service.countConsonant(sentences.toString());

        TextComponent list = service.findSentenceWithTheLongestWord(textComponent.toString());

        service.findEqual(textComponent);
        logger.info(service.findEqual(textComponent));

        //service.countEqualWords(textComponent, "its");

    }
}
