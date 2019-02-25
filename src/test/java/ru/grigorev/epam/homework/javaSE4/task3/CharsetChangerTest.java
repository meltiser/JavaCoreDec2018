package ru.grigorev.epam.homework.javaSE4.task3;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Dmitriy Grigorev
 */
class CharsetChangerTest {

    @Test
    @SneakyThrows
    void test() {
        CharsetChanger.changeUTF8ToUTF16(new File("utf-8"), new File("utf-16"));
        Files.delete(Path.of("utf-16"));
    }
}