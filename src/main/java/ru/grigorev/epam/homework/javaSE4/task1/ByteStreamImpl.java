package ru.grigorev.epam.homework.javaSE4.task1;

import ru.grigorev.epam.homework.javaSE4.common.Common;
import ru.grigorev.epam.homework.javaSE4.common.JavaCodeKeywordsCounterFromFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Прочитайте файл, содержащий код на языке Java.
 * Определите, какие ключевые слова языка Java это код содержит.
 * Выведите эти слова и их количество в другой файл.
 * Используйте только байтовые потоки ввода-вывода.
 *
 * @author Dmitriy Grigorev
 */
public class ByteStreamImpl implements JavaCodeKeywordsCounterFromFile {
    public Map<String, Integer> readKeywordsFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Map<String, Integer> map = new HashMap<>();
        StringBuilder keyword = new StringBuilder();
        while (true) {
            int byteRead = fis.read();
            String charAsString = String.valueOf((char) byteRead);
            if (charAsString.matches("\\W") || byteRead == -1) {
                String keywordString = keyword.toString();
                if (Common.KEYWORDS_JAVA.contains(keywordString)) {
                    map.put(keywordString, map.get(keywordString) == null ? 1 : map.get(keywordString) + 1);
                }
                keyword.setLength(0);
            } else {
                keyword.append(charAsString);
            }
            if (byteRead == -1) break;
        }
        fis.close();
        return map;
    }

    public void writeKeywordCountsToFile(Map<String, Integer> counts, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            fos.write(entry.getKey().getBytes());
            fos.write(" : ".getBytes());
            fos.write(String.valueOf(entry.getValue()).getBytes());
            fos.write(System.lineSeparator().getBytes());
        }
        fos.close();
    }
}
