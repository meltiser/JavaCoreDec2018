package ru.grigorev.epam.homework.javaSE5.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.grigorev.epam.homework.javaSE5.task2.exceptions.NoKeyInPropertiesException;
import ru.grigorev.epam.homework.javaSE5.task2.exceptions.NoPropertiesFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Grigorev
 */
class PropertiesFileReaderTest {
    private final PropertiesFileReader pfr = new PropertiesFileReader();

    @BeforeEach
    void createPropertiesFile() {
        Path path = Path.of("src/main/resources/test.properties");

        List<String> list = new ArrayList<>();
        list.add("key = value");
        list.add("hey : you");
        try {
            Files.createFile(path);
            Files.write(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void deletePropertiesFile() {
        try {
            Files.deleteIfExists(Path.of("src/main/resources/test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readFileTest() {
        pfr.readFile("test.properties");
        Assertions.assertEquals(2,pfr.getProperties().size());
    }

    @Test
    void readFileExceptionTest() {
        Assertions.assertThrows(NoPropertiesFileException.class, () -> pfr.readFile("noFile.properties"));
    }

    @Test
    void getValueByKeyTest() {
        pfr.readFile("test.properties");
        Assertions.assertEquals("value", pfr.getValueByKey("key"));
    }

    @Test
    void getValueByKeyExceptionTest() {
        pfr.readFile("test.properties");
        Assertions.assertThrows(NoKeyInPropertiesException.class, () -> pfr.getValueByKey("noSuchKey"));
    }
}