package ru.grigorev.epam.homework.JavaFundamentals1;

/**
 * @author Dmitriy Grigorev
 */
public class Task3 {

    /**
     * @param x - x argument
     * @return value of "F(x) = tg(2x)-3"
     */
    public static double getY(double x) {
        return Math.tan(2 * x) - 3;
    }

    public static void printValues(int a, int b, int h) {
        for (int i = a; i <= b; i = i + h) {
            System.out.println("x: " + i + " y: " + getY(i));
        }
    }
}
