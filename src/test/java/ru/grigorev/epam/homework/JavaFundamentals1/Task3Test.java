package ru.grigorev.epam.homework.JavaFundamentals1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitriy Grigorev
 */
class Task3Test {

    @Test
    void getYTest1() {
        double from = -5.186;
        double to = -5.184;
        double result = Task3.getY(1);
        boolean isFits = (result > from) && (result < to);
        Assertions.assertTrue(isFits);
    }

    @Test
    void getYTest2() {
        double from = -3.292;
        double to = -3.290;
        double result = Task3.getY(3);
        boolean isFits = (result > from) && (result < to);
        Assertions.assertTrue(isFits);
    }

    @Test
    void getYTest3() {
        double from = -2.352;
        double to = -2.350;
        double result = Task3.getY(5);
        boolean isFits = (result > from) && (result < to);
        Assertions.assertTrue(isFits);
    }

    @Test
    void printValuesFrom1To5Step1() {
        Task3.printValues(1,5,1);
    }
}