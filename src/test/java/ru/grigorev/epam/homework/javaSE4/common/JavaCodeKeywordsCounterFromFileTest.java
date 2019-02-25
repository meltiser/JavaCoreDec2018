package ru.grigorev.epam.homework.javaSE4.common;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.grigorev.epam.homework.javaSE4.task1.ByteStreamImpl;
import ru.grigorev.epam.homework.javaSE4.task2.CharacterStreamImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Dmitriy Grigorev
 */
class JavaCodeKeywordsCounterFromFileTest {

    @Test
    @SneakyThrows
    void testImplementations() {
        JavaCodeKeywordsCounterFromFile counterByte = new ByteStreamImpl();
        JavaCodeKeywordsCounterFromFile counterChar = new CharacterStreamImpl();
        Map<String, Integer> keywordCounts;

        keywordCounts = counterByte.readKeywordsFromFile(new File("text.java"));
        counterByte.writeKeywordCountsToFile(keywordCounts, new File("output.txt"));

        keywordCounts = counterChar.readKeywordsFromFile(new File("text.java"));
        counterChar.writeKeywordCountsToFile(keywordCounts, new File("output2.txt"));

        byte[] f1 = Files.readAllBytes(Paths.get("output.txt"));
        byte[] f2 = Files.readAllBytes(Paths.get("output2.txt"));
        Assertions.assertEquals(f1.length, f2.length);

        Files.delete(Path.of("output.txt"));
        Files.delete(Path.of("output2.txt"));
    }
}
