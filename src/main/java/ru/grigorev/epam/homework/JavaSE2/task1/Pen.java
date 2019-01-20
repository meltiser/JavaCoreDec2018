package ru.grigorev.epam.homework.JavaSE2.task1;

import ru.grigorev.epam.homework.JavaSE2.task3.NoviceKit;
import ru.grigorev.epam.homework.JavaSE2.task3.WritingDevice;

/**
 * Class that encapsulates simple stationary object like pen.
 *
 * @author Dmitriy Grigorev
 */
public class Pen extends WritingDevice implements NoviceKit {
    /**
     * Cost of the pen. Cannot be negative.
     */
    private int cost;

    /**
     * Pen color. Could be blue, black or red. See inner enum class.
     */
    private InkColor inkColor;

    /**
     * Pen name or brand. Can't be set by constructor. By default simply "Pen".
     */
    private String name;

    /**
     * True if pen is ball pan. Otherwise - gel pen
     */
    private boolean isBallPen;

    /**
     * Enum class that contains ink color.
     */
    enum InkColor {
        BLUE,
        BLACK,
        RED
    }

    /**
     * Simple constructor for Pen objects. By default color is blue and it is ball pen.
     *
     * @param cost in conventional units. Can't be negative
     */
    public Pen(int cost) {
        this.cost = cost < 0 ? 0 : cost;
        this.inkColor = InkColor.BLUE;
        this.isBallPen = true;
        this.name = "Pen";
    }

    /**
     * Constructor with cost and ink color params. By default pen is ball pen.
     *
     * @param cost     in conventional units. Can't be negative
     * @param inkColor defines ink color of pen. Use inner enum class Pen for blue, black and red color
     */
    public Pen(int cost, InkColor inkColor) {
        this.cost = cost < 0 ? 0 : cost;
        this.inkColor = inkColor;
        this.isBallPen = true;
        this.name = "Pen";
    }

    /**
     * @param cost in conventional units. Can't be negative
     * @param name name or brand of pen
     */
    public Pen(int cost, String name) {
        this.cost = cost < 0 ? 0 : cost;
        this.inkColor = InkColor.BLUE;
        this.isBallPen = true;
        this.name = name;
    }

    /**
     * Constructor with cost and type of pen params. By default pen color is blue
     *
     * @param cost      in conventional units. Can't be negative
     * @param isBallPen defines type of pen. True - ball pen, otherwise - gel
     */
    public Pen(int cost, boolean isBallPen) {
        this.cost = cost;
        this.inkColor = InkColor.BLUE;
        this.isBallPen = isBallPen;
        this.name = "Pen";
    }

    /**
     * @param cost      in conventional units. Can't be negative
     * @param inkColor  defines ink color of pen. Use inner enum class Pen for blue, black and red color
     * @param isBallPen defines type of pen. True - ball pen, otherwise - gel
     */
    public Pen(int cost, InkColor inkColor, boolean isBallPen) {
        this.cost = cost < 0 ? 0 : cost;
        this.inkColor = inkColor;
        this.isBallPen = isBallPen;
        this.name = "Pen";
    }

    /**
     * Do action like writing device.
     */
    @Override
    public void write() {
        System.out.println("I can write swearing words on documents and u can't erase them!1");
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    /**
     * @param cost can't be negative.
     */
    public void setCost(int cost) {
        this.cost = cost < 0 ? 0 : cost;
    }

    public InkColor getInkColor() {
        return inkColor;
    }

    public void setInkColor(InkColor inkColor) {
        this.inkColor = inkColor;
    }

    public boolean isBallPen() {
        return isBallPen;
    }

    public void setBallPen(boolean ballPen) {
        isBallPen = ballPen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pen)) return false;

        Pen pen = (Pen) o;

        if (cost != pen.cost) return false;
        if (isBallPen != pen.isBallPen) return false;
        if (inkColor != pen.inkColor) return false;
        return name != null ? name.equals(pen.name) : pen.name == null;
    }

    @Override
    public int hashCode() {
        int result = cost;
        result = 31 * result + (inkColor != null ? inkColor.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isBallPen ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        String typeOfPen = isBallPen ? "Ball" : "Gel";
        return typeOfPen +
                " pen (named " + name +
                "), costs " + cost +
                " conventional units" +
                " and " + inkColor.toString().toLowerCase() + " ink color.";
    }
}
