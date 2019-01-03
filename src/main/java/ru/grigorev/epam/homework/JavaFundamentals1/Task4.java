package ru.grigorev.epam.homework.JavaFundamentals1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmitriy Grigorev
 */
public class Task4 {

    public static double getMaxInSpecialArrayRandomValues(int length) {
        double[] array = new double[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random() * 100;
            System.out.println(array[i]);
        }
        return getMaxInSpecialArray(array);
    }

    public static double getMaxInSpecialArrayCustomValues(double ... array) {
        return getMaxInSpecialArray(array);
    }

    /**
     * @param array - sequence of real numbers
     * @return max(a(1) + a(2n), a(2) + a(2n-1), ... , a(n) + a(n+1)).
     */
    private static double getMaxInSpecialArray(double[] array) {
        List<Double> list = new ArrayList<>();

        for (int i = 0; i < array.length - 1; i++) {
            list.add(array[i] + array[i + 1]);
        }
        return Collections.max(list);
    }
}
