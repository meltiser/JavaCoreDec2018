package ru.grigorev.epam.homework.JavaSE2.task3;

/**
 * @author Dmitriy Grigorev
 */
public class Ruler extends Stationary {
    private int cost;

    public Ruler(int cost) {
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ruler{" +
                "cost=" + cost +
                '}';
    }
}
