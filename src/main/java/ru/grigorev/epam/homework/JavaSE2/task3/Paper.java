package ru.grigorev.epam.homework.JavaSE2.task3;

/**
 * @author Dmitriy Grigorev
 */
public class Paper extends Stationary implements NoviceKit {
    private int cost;
    private String name;

    public Paper(int cost) {
        this.cost = cost;
        this.name = "Paper";
    }

    public Paper(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
