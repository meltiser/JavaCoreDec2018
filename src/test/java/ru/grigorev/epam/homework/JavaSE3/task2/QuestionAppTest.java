package ru.grigorev.epam.homework.JavaSE3.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitriy Grigorev
 */
class QuestionAppTest {

    @Test
    void propertyUITest() {
        QuestionApp questionApp = new QuestionApp();
        assertTrue(questionApp.getBundleUI().containsKey("getQuestionCount"));
    }

    @Test
    void checkLanguageChange() {
        QuestionApp questionApp = new QuestionApp();

        questionApp.setRU();
        String actualRu = questionApp.getBundleUI().getString("goodbye");
        assertEquals("Пока!", actualRu);
        questionApp.setEN();
        String actualEn = questionApp.getBundleUI().getString("goodbye");
        assertEquals("Goodbye!", actualEn);
    }

    @Test
    void checkRightInput() {
        QuestionApp questionApp = new QuestionApp();

        String quesCount = questionApp.getBundleUI().getString("getQuestionCount");

        assertFalse(questionApp.checkRightInput(quesCount + "1"));
        assertFalse(questionApp.checkRightInput("DVA"));
        assertTrue(questionApp.checkRightInput("1"));
    }

}