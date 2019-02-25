package ru.grigorev.epam.homework.javaSE4.task2;

import ru.grigorev.epam.homework.javaSE4.common.Common;
import ru.grigorev.epam.homework.javaSE4.common.JavaCodeKeywordsCounterFromFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Прочитайте файл, содержащий код на языке Java.
 * Определите, какие ключевые слова языка Java это код содержит.
 * Выведите эти слова и их количество в другой файл.
 * Используйте только символьные потоки ввода-вывода.
 *
 * @author Dmitriy Grigorev
 */
public class CharacterStreamImpl implements JavaCodeKeywordsCounterFromFile {
    public Map<String, Integer> readKeywordsFromFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        Map<String, Integer> map = new HashMap<>();
        String s;
        while ((s = br.readLine()) != null) {
            String[] words = s.split("\\s+");
            for (String word : words) {
                if (Common.KEYWORDS_JAVA.contains(word)) {
                    map.put(word, map.get(word) == null ? 1 : map.get(word) + 1);
                }
            }
        }
        br.close();
        return map;
    }

    public void writeKeywordCountsToFile(Map<String, Integer> counts, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            fw.write(entry.getKey() + " : " + entry.getValue() + System.lineSeparator());
        }
        fw.close();
    }
}
