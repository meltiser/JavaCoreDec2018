package ru.grigorev.epam.homework.JavaSE3.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitriy Grigorev
 */
class ParserTest {

    @Test
    void isReferencesToPicturesGoesConsistently() {
        Parser parser = new Parser("This is sparta (Рис. 1). Have you ever seen the groundhog(Рис. 3)?");
        assertTrue(parser.isReferencesToPicturesGoesConsistently());
        parser.setText("Don't wanna close my eyes (Рис. 4) Don't wanna fall asleep (рис. 3)");
        assertFalse(parser.isReferencesToPicturesGoesConsistently());
        parser.setText("MA DER FREND (Рис.1) Look at my horse (рис. 2) YOLO(Рис. 3) spam eggs (рис.2)");
        assertTrue(parser.isReferencesToPicturesGoesConsistently());
    }

    @Test
    void getAllSentencesWithPicturesReferences() {
        Parser parser = new Parser("Невозможно рассказать (рис. 2). Вам историю мою. Слишко сложно (рис. 7)");
        List<String> sentences = parser.getAllSentencesWithPicturesReferences();
        assertEquals(2, sentences.size());
    }
}