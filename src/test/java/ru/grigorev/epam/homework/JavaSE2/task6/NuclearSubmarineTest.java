package ru.grigorev.epam.homework.JavaSE2.task6;

import org.junit.jupiter.api.Test;

/**
 * @author Dmitriy Grigorev
 */
class NuclearSubmarineTest {

    @Test
    void run() {
        System.out.println("----------------------------");
        NuclearSubmarine nuclearSubmarine = new NuclearSubmarine();
        nuclearSubmarine.getSubmarineEngine().turnOn();
        nuclearSubmarine.run();
    }
}