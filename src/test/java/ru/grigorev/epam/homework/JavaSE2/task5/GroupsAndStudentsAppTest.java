package ru.grigorev.epam.homework.JavaSE2.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Dmitriy Grigorev
 */
class GroupsAndStudentsAppTest {
    private Student ivanov;
    private Student petrov;
    private Student sidorov;
    private Student kotov;
    private Student pupkin;
    private Student javascriptov;

    private Group<Double> mathGroup;
    private Group<Integer> historyGroup;
    private Group<Integer> chemistryGroup;

    private GroupsAndStudentsApp app;

    @BeforeEach
    void initTest() {
        ivanov = new Student("Ivanov");
        petrov = new Student("Petrov");
        sidorov = new Student("Sidorov");
        kotov = new Student("Kotov");
        pupkin = new Student("Pupkin");
        javascriptov = new Student("Javascriptov");

        mathGroup = new Group<>(Subject.MATH);
        mathGroup.addStudent(ivanov, 3.5);
        mathGroup.addStudent(petrov, 4.6);
        mathGroup.addStudent(sidorov, 4.9);
        mathGroup.addStudent(kotov, 4.2);

        historyGroup = new Group<>(Subject.HISTORY);
        historyGroup.addStudent(petrov, 3);
        historyGroup.addStudent(sidorov, 4);
        historyGroup.addStudent(pupkin, 5);

        chemistryGroup = new Group<>(Subject.CHEMISTRY);
        chemistryGroup.addStudent(sidorov, 5);
        chemistryGroup.addStudent(pupkin, 2);

        app = new GroupsAndStudentsApp();
        app.addGroup(mathGroup);
        app.addGroup(historyGroup);
        app.addGroup(chemistryGroup);
    }

    @Test
    void getStudentProgressAmongOthersTest1() {
        String result = mathGroup.getStudentProgressAmongOthers(sidorov);
        Assertions.assertEquals("best result", result);
    }

    @Test
    void getStudentProgressAmongOthersTest2() {
        String result = chemistryGroup.getStudentProgressAmongOthers(pupkin);
        Assertions.assertEquals("worst result", result);
    }

    @Test
    void getStudentProgressAmongOthersTest3() {
        String result = historyGroup.getStudentProgressAmongOthers(sidorov);
        Assertions.assertEquals("2 out of 3", result);
    }

    @Test
    void getStudentInfoTest() {
        String result = app.getStudentInfo(javascriptov);
        Assertions.assertTrue(result.endsWith("doesn't have any groups"));
    }

    @Test
    void showStudentInfoTestConsole() {
        System.out.println("----------------------------");
        app.showStudentInfo(sidorov);
    }
}