package ru.grigorev.epam.homework.JavaSE2.task3;

/**
 * @author Dmitriy Grigorev
 */
public class Highlighter extends WritingDevice {
    private int cost;

    public Highlighter(int cost) {
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
    public void write() {
        System.out.println("why am i exist if u can just underline words?");
    }

    @Override
    public String toString() {
        return "Highlighter{" +
                "cost=" + cost +
                '}';
    }
}
