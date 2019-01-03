package ru.grigorev.epam.homework.JavaFundamentals1;

/**
 * @author Dmitriy Grigorev
 */
public class Task5 {

    public static int[][] getMatrix() {
        int[][] array = new int[6][7];
        int counter1 = 0;
        int counter2 = 6;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][counter1] = 1;
                array[i][counter2] = 1;
            }

            if (i < 2) {
                counter1++;
                counter2--;
            } else if (i == 2) {
                continue;
            } else {
                counter1--;
                counter2++;
            }
        }
        return array;
    }

    public static void printMatrix(int[][] matrix) {
        int middle = matrix.length / 2;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == middle) System.out.printf("%2s", "□");
                else if (j == middle) System.out.printf("%2s", "□");
                else System.out.printf("%2d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}