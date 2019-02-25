package ru.grigorev.epam.homework.javaSE4.common;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Dmitriy Grigorev
 */
public interface JavaCodeKeywordsCounterFromFile {
    void writeKeywordCountsToFile(Map<String, Integer> counts, File file) throws IOException;

    /**
     * Counts java reserved keywords
     *
     * @param file to write in
     * @return map where key - keyword, value - count of keyword present
     * @throws IOException
     */
    Map<String, Integer> readKeywordsFromFile(File file) throws IOException;
}
