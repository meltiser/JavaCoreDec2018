package ru.grigorev.epam.homework.JavaFundamentals1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Dmitriy Grigorev
 */
class Task4Test {
    @Test
    void getMaxFromSpecialArrayTest1() {
        double expected = 10.4;
        double result = Task4.getMaxInSpecialArrayCustomValues(3, 7.4, 1.4, 6);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getMaxFromSpecialArrayTest2() {
        double expected = 36; // 35.73
        double result = Math.round(Task4.getMaxInSpecialArrayCustomValues(4, 20.1, 15.63));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getMaxFromSpecialArrayTest3() {
        double expected = 11;
        double result = Task4.getMaxInSpecialArrayCustomValues(0, -4, 5, 6, 2.354);
        Assertions.assertEquals(expected, result);
    }
}