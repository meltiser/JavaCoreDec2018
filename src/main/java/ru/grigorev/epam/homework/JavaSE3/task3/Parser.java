package ru.grigorev.epam.homework.JavaSE3.task3;

import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Необходимо определить в тексте статьи (html-файл), ссылается ли автор на рисунки
 * последовательно или нет, а также выделить все предложения, в которых встречаются ссылки на
 * рисунки. Для разбора текста использовать регулярные выражения.
 *
 * @author Dmitriy Grigorev
 */
public class Parser {
    public static final String FILE_NAME = "Java.SE.03.Information handling_task_attachment.html";

    @Setter
    private String text;

    public Parser(File file, String charset) throws IOException {
        Document document = Jsoup.parse(file, charset);
        text = document.body().text();
    }

    public Parser(String text) {
        this.text = text;
    }

    /**
     * @return true if number of picture goes one after another, or picture reference has been earlier e.g.
     * "рис. 1 ... рис. 2 ... рис. 3" or "рис. 1 ... рис. 2 ... рис. 3 ... рис. 2" (also true).
     * Otherwise "рис. 1 ... рис. 3 ... рис. 2" will return false
     */
    public boolean isReferencesToPicturesGoesConsistently() {
        Pattern pattern = Pattern.compile("([рР]ис\\. *\\d+)");
        Matcher matcher = pattern.matcher(text);

        int pictureNumber = 1;
        Set<Integer> foundPictures = new HashSet<>();

        while (matcher.find()) {
            String found = text.substring(matcher.start(), matcher.end());
            int spaceIndex = found.lastIndexOf(' ');
            if (spaceIndex == -1) spaceIndex = found.lastIndexOf('.');
            int currentPictureNumber = Integer.parseInt(found.substring(spaceIndex + 1));
            if (currentPictureNumber >= pictureNumber) {
                pictureNumber = currentPictureNumber;
                foundPictures.add(currentPictureNumber);
            } else {
                if (!foundPictures.contains(currentPictureNumber)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Divides text into sentences and returns only those which have picture references.
     *
     * @return {@link List} of sentences
     */
    public List<String> getAllSentencesWithPicturesReferences() {
        Pattern pattern = Pattern.compile("([рР]ис\\. *\\d+)");

        BreakIterator breakIterator = BreakIterator.getSentenceInstance(Locale.getDefault());
        breakIterator.setText(text);

        List<String> sentences = new ArrayList<>();
        int lastIndex = breakIterator.first();
        while (lastIndex != BreakIterator.DONE) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();

            if (lastIndex != BreakIterator.DONE) {
                String sentence = text.substring(firstIndex, lastIndex);
                if (pattern.matcher(sentence).find()) {
                    sentences.add(sentence);
                }
            }
        }

        return sentences;
    }

    public static void main(String[] args) {
        try {
            Parser parser = new Parser(new File(FILE_NAME), "cp1251");
            System.out.println(parser.isReferencesToPicturesGoesConsistently());
            parser.getAllSentencesWithPicturesReferences().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
