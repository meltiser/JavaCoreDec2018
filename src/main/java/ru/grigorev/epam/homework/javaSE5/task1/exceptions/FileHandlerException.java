package ru.grigorev.epam.homework.javaSE5.task1.exceptions;

import ru.grigorev.epam.homework.javaSE5.task1.FilesHandler;

/**
 * This exception is thrown if something went wrong in {@link FilesHandler} methods.
 * Used to wrap {@link java.io.IOException} and handle other issues.
 *
 * @author Dmitriy Grigorev
 */
public class FileHandlerException extends Exception {
    public FileHandlerException(String message) {
        super(message);
    }

    public FileHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
