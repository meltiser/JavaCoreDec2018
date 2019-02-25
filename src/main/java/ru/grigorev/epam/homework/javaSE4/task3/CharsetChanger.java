package ru.grigorev.epam.homework.javaSE4.task3;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Дан файл, содержащий буквы текст на кириллице.
 * Кодировка файла utf-8. Прочитайте информацию из файла и перепишите ее в файл в кодировкой utf-16.
 *
 * @author Dmitriy Grigorev
 */
public interface CharsetChanger {
    static void changeUTF8ToUTF16(File from, File to) throws IOException {
        Reader in = new InputStreamReader(new FileInputStream(from), StandardCharsets.UTF_8);
        Writer out = new OutputStreamWriter(new FileOutputStream(to), StandardCharsets.UTF_16);

        int ch;
        while ((ch = in.read()) != -1) {
            out.write(ch);
        }

        in.close();
        out.close();
    }
}
