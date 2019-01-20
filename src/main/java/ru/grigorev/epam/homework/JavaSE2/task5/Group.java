package ru.grigorev.epam.homework.JavaSE2.task5;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for forming students into groups.
 * Each group must have subject to study and type of mark, which estimates students knowledge.
 * Mark extends class Number, so it could be either real or integer.
 * All students and their marks are stored in map.
 *
 * @author Dmitriy Grigorev
 */
public class Group<T extends Number & Comparable<? super T>> {
    /**
     * Subject group is studying. {@link Subject}
     */
    private Subject subject;
    private Map<Student, T> students;

    /**
     * Initialises group with subject to study and hashmap for storing students and their marks.
     *
     * @param subject {@link Subject}
     */
    public Group(Subject subject) {
        this.subject = subject;
        students = new HashMap<>();
    }

    public Subject getSubject() {
        return subject;
    }

    /**
     * Adds student to map
     *
     * @param student {@link Student} can't be null
     * @param mark    cant' be less than zero
     */
    public void addStudent(Student student, T mark) {
        if (student == null || mark == null || mark.doubleValue() < 0 || mark.intValue() < 0) return;
        students.put(student, mark);
    }

    public T getStudentMark(Student student) {
        return students.get(student);
    }

    public boolean containsStudent(Student student) {
        return students.containsKey(student);
    }

    /**
     * Sorts map {@link Group#sortStudentsFromBestMarkToWorst()} to return student academic performance
     *
     * @param student {@link Student} to be known
     * @return string contains result of the student.
     * Unless it's "best result" or "worst result",
     * returns position in the group among number of students e.g. "3 out of 7"
     */
    public String getStudentProgressAmongOthers(Student student) {
        sortStudentsFromBestMarkToWorst();
        int count = 0;
        for (Map.Entry<Student, T> entry : students.entrySet()) {
            count++;
            if (entry.getKey().equals(student)) break;
        }

        if (count == 1) return "best result";
        else if (count == students.size()) return "worst result";
        else return count + " out of " + students.size();
    }

    /**
     * Sorts students map from highest value to lowest.
     * To guarantee order after sorting, stream is collected to LinkedHashMap
     */
    private void sortStudentsFromBestMarkToWorst() {
        students = students.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Shows all students in the console one by another
     */
    public void showAllStudents() {
        System.out.println("Group " + subject + " includes: ");
        for (Map.Entry<Student, T> studentTEntry : students.entrySet()) {
            System.out.println(studentTEntry.getKey() + " mark is " + studentTEntry.getValue());
        }
    }
}
