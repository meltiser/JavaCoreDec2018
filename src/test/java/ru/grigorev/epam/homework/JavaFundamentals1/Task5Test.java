package ru.grigorev.epam.homework.JavaFundamentals1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitriy Grigorev
 */
class Task5Test {
    private int[][] expectedArr = {
            {1,0,0,0,0,0,1},
            {0,1,0,0,0,1,0},
            {0,0,1,0,1,0,0},
            {0,0,1,0,1,0,0},
            {0,1,0,0,0,1,0},
            {1,0,0,0,0,0,1},
    };

    @Test
    void getMatrixTest() {
        int[][] result = Task5.getMatrix();
        Assertions.assertArrayEquals(expectedArr, result);
    }

    @Test
    void printMatrix() {
        Task5.printMatrix(Task5.getMatrix());
    }
}