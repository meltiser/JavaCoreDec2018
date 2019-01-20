package ru.grigorev.epam.homework.JavaSE2.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.grigorev.epam.homework.JavaSE2.task1.Pen;
import ru.grigorev.epam.homework.JavaSE2.task3.NoviceKit;
import ru.grigorev.epam.homework.JavaSE2.task3.Paper;
import ru.grigorev.epam.homework.JavaSE2.task3.Pencil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Grigorev
 */
class NoviceKitSortAppTest {

    @Test
    void sortByCost() {
        Pen expectedPen = new Pen(19, "d");

        List<NoviceKit> list = new ArrayList<>();
        list.add(new Pen(23, "c"));
        list.add(new Paper(40, "d"));
        list.add(expectedPen);
        list.add(new Pencil(29, "b"));

        NoviceKitSortApp noviceKitSortApp = new NoviceKitSortApp();
        noviceKitSortApp.sortByCost(list);

        Assertions.assertEquals(expectedPen, list.get(0));
    }

    @Test
    void sortByName() {
        Pen expectedPen = new Pen(26, "a");

        List<NoviceKit> list = new ArrayList<>();
        list.add(new Pen(23, "c"));
        list.add(new Paper(40, "d"));
        list.add(expectedPen);
        list.add(new Pencil(29, "b"));

        NoviceKitSortApp noviceKitSortApp = new NoviceKitSortApp();
        noviceKitSortApp.sortByName(list);

        Assertions.assertEquals(expectedPen, list.get(0));
    }

    @Test
    void sortByCostThenName() {
        Pen expectedPen = new Pen(23, "a");

        List<NoviceKit> list = new ArrayList<>();
        list.add(new Pen(23, "c"));
        list.add(expectedPen);
        list.add(new Paper(40, "d"));
        list.add(new Pencil(50, "b"));

        NoviceKitSortApp noviceKitSortApp = new NoviceKitSortApp();
        noviceKitSortApp.sortByCostThenName(list);

        Assertions.assertEquals(expectedPen, list.get(0));
    }
}