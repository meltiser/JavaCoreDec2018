package ru.grigorev.epam.homework.javaSE5.task2;

import lombok.Getter;
import ru.grigorev.epam.homework.javaSE5.task2.exceptions.NoKeyInPropertiesException;
import ru.grigorev.epam.homework.javaSE5.task2.exceptions.NoPropertiesFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Создать “универсальный” класс, позволяющий получить значение из любого properties-файла.
 * Физическое чтение файла должно происходить только один раз.
 * Обработайте следующие исключительные ситуации: нет файла *.properties, нет ключа в properties-файле.
 *
 * @author Dmitriy Grigorev
 */
public class PropertiesFileReader {
    @Getter
    private Map<String, String> properties = new HashMap<>();

    public void readFile(String fileName) {
        if (!fileName.endsWith(".properties")) {
            return;
        }

        Path path = Path.of("src/main/resources/" + fileName);
        if (!path.toFile().exists()) throw new NoPropertiesFileException();

        try (Stream<String> lines = Files.lines(path)) {

            lines.filter(s -> !s.isEmpty()).forEach(s -> {
                int index = s.indexOf('=');
                if (index == -1) index = s.indexOf(':');
                properties.put(s.substring(0, index).trim(),
                        s.substring(index + 1).trim());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValueByKey(String key) {
        if (properties.containsKey(key)) {
            return properties.get(key);
        } else {
            throw new NoKeyInPropertiesException();
        }
    }
}
