package ru.grigorev.epam.homework.JavaSE2.task5;

import java.util.ArrayList;
import java.util.List;

/**
 * An app that is allowed user to find out info about student.
 * Add group using {@link GroupsAndStudentsApp#addGroup(Group)}
 * and search the student you need by {@link GroupsAndStudentsApp#showStudentInfo(Student)}.
 *
 * @author Dmitriy Grigorev
 */
public class GroupsAndStudentsApp {
    private List<Group> groups = new ArrayList<>();

    public void addGroup(Group group) {
        groups.add(group);
    }

    /**
     * Shows info about student to console.
     *
     * @param student {@link Student} to be known
     */
    public void showStudentInfo(Student student) {
        System.out.println(getStudentInfo(student));
    }

    /**
     * Returns info about student.
     *
     * @param student {@link Student} to be known
     * @return name of student, groups he's into, mark and progress
     */
    public String getStudentInfo(Student student) {
        StringBuilder s = new StringBuilder();
        int count = 0;
        for (Group group : groups) {
            if (group.containsStudent(student)) {
                s.append(String.format("%s with mark %s (%s), \n",
                        group.getSubject().toString(),
                        group.getStudentMark(student),
                        group.getStudentProgressAmongOthers(student)));
                count++;
            }
        }
        if (count == 0) {
            return student.getName() + " doesn't have any groups";
        } else {
            // replacing last ', \n' with '.'
            return student.getName() + " is in groups: " + s.substring(0, s.length() - 3) + ".";
        }
    }
}
