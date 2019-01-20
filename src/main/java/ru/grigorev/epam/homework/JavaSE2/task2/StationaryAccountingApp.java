package ru.grigorev.epam.homework.JavaSE2.task2;

import ru.grigorev.epam.homework.JavaSE2.task3.Stationary;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for keeping records of stationary. Items stored in ArrayList.
 *
 * @author Dmitriy Grigorev
 */
public class StationaryAccountingApp {
    private List<Stationary> stationaryList = new ArrayList<>();

    public void addStationaryItem(Stationary item) {
        stationaryList.add(item);
    }

    /**
     * @param cost of an item
     * @return found item
     * @throws Exception if no item found
     */
    public Stationary getItemByCost(int cost) throws Exception {
        for (Stationary stationary : stationaryList) {
            if (stationary.getCost() == cost) return stationary;
        }
        throw new Exception("No item with such cost!");
    }

    /**
     * Deletes first found item by its cost.
     *
     * @param cost of an item
     * @return true if deleted, otherwise - false
     */
    public boolean deleteItemByCost(int cost) {
        for (Stationary stationary : stationaryList) {
            if (stationary.getCost() == cost) {
                stationaryList.remove(stationary);
                return true;
            }
        }
        return false;
    }

    /**
     * @return sum of all stationary
     */
    public int getFullCostOfStationary() {
        return stationaryList.stream().mapToInt(Stationary::getCost).sum();
    }

    /**
     * Shows items one by another
     */
    public void showAllStationary() {
        stationaryList.forEach(System.out::println);
    }

    /**
     * @return number of stationary that currently accounted
     */
    public int getStationaryCount() {
        return stationaryList.size();
    }

}
