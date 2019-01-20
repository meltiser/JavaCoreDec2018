package ru.grigorev.epam.homework.JavaSE2.task6;

import ru.grigorev.epam.homework.JavaSE2.task7.Tannotation;

/**
 * @author Dmitriy Grigorev
 */
@Tannotation
public class NuclearSubmarine {
    private SubmarineEngine submarineEngine = new SubmarineEngine();

    public SubmarineEngine getSubmarineEngine() {
        return submarineEngine;
    }

    public void run() {
        if (submarineEngine.isOn) {
            System.out.println("Accelerating...");
        } else {
            System.out.println("Needs to turn on engine first!");
            return;
        }
        System.out.println("Open ocean!");
    }

    public class SubmarineEngine {
        private boolean isOn;

        public void turnOn() {
            isOn = true;
            System.out.println("Engine's on!");
        }

        public void turnOff() {
            isOn = false;
            System.out.println("Engine's off!");
        }
    }
}
