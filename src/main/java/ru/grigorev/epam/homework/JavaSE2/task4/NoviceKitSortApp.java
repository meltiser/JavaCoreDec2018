package ru.grigorev.epam.homework.JavaSE2.task4;

import ru.grigorev.epam.homework.JavaSE2.task3.NoviceKit;

import java.util.Comparator;
import java.util.List;

/**
 * Class for sorting objects that implement {@link NoviceKit}. Items can be sorted by cost, name or by cost and name
 *
 * @author Dmitriy Grigorev
 */
public class NoviceKitSortApp {
    public List<NoviceKit> sortByCost(List<NoviceKit> list) {
        list.sort(Comparator.comparing(NoviceKit::getCost));
        return list;
    }

    public List<NoviceKit> sortByName(List<NoviceKit> list) {
        list.sort(Comparator.comparing(NoviceKit::getName));
        return list;
    }

    public List<NoviceKit> sortByCostThenName(List<NoviceKit> list) {
        Comparator<NoviceKit> costThenNameComparator = Comparator.comparing(NoviceKit::getCost)
                .thenComparing(NoviceKit::getName);
        list.sort(costThenNameComparator);
        return list;
    }
}
