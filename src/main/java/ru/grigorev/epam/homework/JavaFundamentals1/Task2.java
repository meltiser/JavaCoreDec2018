package ru.grigorev.epam.homework.JavaFundamentals1;

/**
 * @author Dmitriy Grigorev
 */
public class Task2 {

    private static double calcElement(int n) {
        return 1 / Math.pow(n + 1, 2);
    }

    /**
     * @param E - sum of the sequence
     * @return min element from sequence that fits M: a(n) < E condition
     */
    public static int findMinElement(double E) {
        double a = 0;
        int n = 0;
        do {
            n++;
            a = calcElement(n);
        } while (a > E);
        return n;
    }

    public static void printSequence(int n) {
        System.out.println("N = " + n);
        for (int i = 1; i <= n; i++) {
            System.out.println("a" + i + " = " + calcElement(i));
        }
    }
}
