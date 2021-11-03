package by.matsukiryna.information_handling.reader.impl;

import by.matsukiryna.information_handling.exception.CompositeException;
import by.matsukiryna.information_handling.reader.TextReader;
import by.matsukiryna.information_handling.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_DELIMITER_REGEX = "\n";

    @Override
    public String readFile(String filePath) throws CompositeException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filePath);
        String strPath = new File(resource.getFile()).getPath();
        Path path = Paths.get(strPath);
        String text;

        if (!FileValidator.isFileValidate(strPath)) {
            logger.error("File name is null or empty " + filePath);
            throw new CompositeException("File name is null or empty " + filePath);
        }

        try (Stream<String> streamLines = Files.lines(path)) {
            text = streamLines.collect(Collectors.joining(WORD_DELIMITER_REGEX));
            logger.info("Text from the file: " + text);
        } catch (IOException e) {
            logger.error(e);
            throw new CompositeException("Error during reading file " + e.getMessage());
        }
        return text;
    }
}
