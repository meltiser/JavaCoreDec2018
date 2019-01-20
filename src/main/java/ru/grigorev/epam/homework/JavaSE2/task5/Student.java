package ru.grigorev.epam.homework.JavaSE2.task5;

/**
 * @author Dmitriy Grigorev
 */
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
