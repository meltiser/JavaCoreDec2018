package ru.grigorev.epam.homework.javaSE5.task1;

import ru.grigorev.epam.homework.javaSE5.task1.exceptions.FileHandlerException;

/**
 * @author Dmitriy Grigorev
 */
public interface FilesHandler {
    /**
     * Opens file in default desktop program (if it's possible).
     *
     * @param absoluteDir
     * @throws FileHandlerException
     */
    void openFile(String absoluteDir) throws FileHandlerException;

    /**
     * Creates file in a directory. File name must be passed with extension and absolute directory.
     *
     * @param absoluteDir
     * @throws FileHandlerException
     */
    void createFile(String absoluteDir) throws FileHandlerException;

    /**
     * Deletes file in a directory. Rules are same as for {@link FilesHandler#createFile(String)}
     *
     * @param absoluteDir
     * @throws FileHandlerException
     */
    void deleteFile(String absoluteDir) throws FileHandlerException;

    /**
     * Edits .txt file in a directory. Appends data to the end of the file (doesn't rewrite it!).
     *
     * @param absoluteDir
     * @param dataToWrite
     * @throws FileHandlerException
     */
    void editTxtFile(String absoluteDir, String dataToWrite) throws FileHandlerException;

    /**
     * Returns .txt file content.
     *
     * @param absoluteDir
     * @return lines in file
     * @throws FileHandlerException
     */
    String getTxtContent(String absoluteDir) throws FileHandlerException;

    /**
     * Returns names of folders and files in directory.
     *
     * @param absoluteDir
     * @return string with {@link System#lineSeparator()} between names
     * @throws FileHandlerException
     */
    String getCurrentFolderContent(String absoluteDir) throws FileHandlerException;

    /**
     * Check if it's possible to change directory.
     * If everything's fine returns absolute name of changed directory.
     *
     * @param currentDirectory
     * @param directoryTo
     * @return string with absolute directory
     * @throws FileHandlerException
     */
    String changeDirectory(String currentDirectory, String directoryTo) throws FileHandlerException;
}
