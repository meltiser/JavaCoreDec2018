package ru.grigorev.epam.homework.JavaSE2.task3;

/**
 * @author Dmitriy Grigorev
 */
public class Pencil extends WritingDevice implements NoviceKit {
    private int cost;
    private String name;

    public Pencil(int cost) {
        this.cost = cost;
        this.name = "Pencil";
    }

    public Pencil(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void write() {
        System.out.println("U can erase me, but never defeat!1");
    }

    @Override
    public String toString() {
        return "Pencil{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
