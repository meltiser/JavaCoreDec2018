package ru.grigorev.epam.homework.JavaSE2.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.grigorev.epam.homework.JavaSE2.task1.Pen;
import ru.grigorev.epam.homework.JavaSE2.task3.*;

/**
 * @author Dmitriy Grigorev
 */
class StationaryAccountingAppTest {
    private StationaryAccountingApp app = new StationaryAccountingApp();

    @Test
    void addStationaryItem() {
        Pen pen = new Pen(20);
        app.addStationaryItem(pen);
        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, app.getStationaryCount());
    }

    @Test
    void getItemByCost() {
        Pencil pencil = new Pencil(10);
        app.addStationaryItem(pencil);
        Stationary result = null;
        try {
            result = app.getItemByCost(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(pencil, result);
    }

    @Test
    void deleteItemByCost() {
        Paper paper = new Paper(40);
        app.addStationaryItem(paper);
        app.deleteItemByCost(40);
        Assertions.assertEquals(0, app.getStationaryCount());
    }

    @Test
    void getFullCostOfStationary() {
        app.addStationaryItem(new Paper(50));
        app.addStationaryItem(new Pencil(10));
        app.addStationaryItem(new Pen(10));
        int result = app.getFullCostOfStationary();
        Assertions.assertEquals(70, result);
    }

    @Test
    void showAllStationary() {
        System.out.println("----------------------------");
        app.addStationaryItem(new Paper(50));
        app.addStationaryItem(new Pencil(10));
        app.addStationaryItem(new Pen(15));
        app.addStationaryItem(new Highlighter(20));
        app.addStationaryItem(new Ruler(5));
        app.showAllStationary();
    }
}