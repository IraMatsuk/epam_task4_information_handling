package by.matsukiryna.information_handling.validator;

import by.matsukiryna.information_handling.exception.CompositeException;

import java.io.File;

public class FileValidator {
    public static boolean isFileValidate(String filePath) throws CompositeException {
        if (filePath == null || filePath.isBlank()) {
            throw new CompositeException("File name is null or empty " + filePath);
        }
        File file = new File(filePath);
        if (file.exists() && file.canRead() && file.isFile() && file.length() > 0) {
            return true;
        }
        return false;
    }
}
