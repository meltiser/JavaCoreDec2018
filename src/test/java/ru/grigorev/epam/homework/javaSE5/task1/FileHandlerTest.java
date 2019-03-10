package ru.grigorev.epam.homework.javaSE5.task1;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.grigorev.epam.homework.javaSE5.task1.exceptions.FileHandlerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Dmitriy Grigorev
 */
class FileHandlerTest {
    private FileHandler fileHandler = new FileHandlerImpl();
    private String directory = System.getProperty("user.dir") + "\\";

    @Test
    @SneakyThrows
    void changeDirectoryTest() {
        String directoryTo = "src";
        String result = fileHandler.changeDirectory(directory, directoryTo);
        assertEquals(directory + directoryTo + "\\", result);
    }

    @Test
    @SneakyThrows
    void getCurrentFolderContentTest() {
        String directoryGet = directory + "src\\test\\java\\ru\\grigorev\\epam\\homework\\javaSE5\\task1";
        String result = fileHandler.getCurrentFolderContent(directoryGet);
        assertEquals("FileHandlerTest.java", result);
    }

    @Test
    @SneakyThrows
    void getTxtContentAndEditFileTest() {
        String testFileDirectory = directory + "test.txt";
        fileHandler.createFile(testFileDirectory);
        fileHandler.editTxtFile(testFileDirectory, "TEST");
        String result = fileHandler.getTxtContent(testFileDirectory);
        assertEquals("TEST" + System.lineSeparator(), result);
        fileHandler.deleteFile(testFileDirectory);
    }

    @Test
    void openFileExceptionTest() {
        assertThrows(FileHandlerException.class,
                () -> fileHandler.openFile(directory + "src"),
                "Path is directory!");

        assertThrows(FileHandlerException.class,
                () -> fileHandler.openFile(directory + "not existing file"),
                "File doesn't not exist!");
    }

    @Test
    void getTxtContentExceptionTest() {
        assertThrows(FileHandlerException.class,
                () -> fileHandler.getTxtContent(directory + "pom.xml"),
                "The app can read only .txt files!");
    }

    @Test
    void editTxtFileExceptionTest() {
        assertThrows(FileHandlerException.class,
                () -> fileHandler.editTxtFile(directory + "pom.xml", "something"),
                "The app can edit only .txt files!");
    }

    @Test
    void changeDirectoryExceptionTest() {
        assertThrows(FileHandlerException.class,
                () -> fileHandler.changeDirectory(directory, "not existing directory"),
                "Directory doesn't exist!");

        assertThrows(FileHandlerException.class,
                () -> fileHandler.changeDirectory(directory, "pom.xml"),
                "Input directory is a file!");
    }
}
