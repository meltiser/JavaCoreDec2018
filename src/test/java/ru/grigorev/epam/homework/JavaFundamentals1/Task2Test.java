package ru.grigorev.epam.homework.JavaFundamentals1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Dmitriy Grigorev
 */
class Task2Test {

    @Test
    void findMinElementTest1() {
        int expected = 1;
        int result = Task2.findMinElement(0.5);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void findMinElementTest2() {
        int expected = 3;
        int result = Task2.findMinElement(0.1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void findMinElementTest3() {
        int expected = 5;
        int result = Task2.findMinElement(0.03);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void printSequenceForN5() {
        Task2.printSequence(5);
    }
}