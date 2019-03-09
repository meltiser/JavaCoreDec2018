package ru.grigorev.epam.homework.javaSE5.task1;

import ru.grigorev.epam.homework.javaSE5.task1.exceptions.ConsoleManagerException;

/**
 * Разработать приложение, позволяющее просматривать файлы и каталоги файловой системы,
 * а также создавать и удалять текстовые файлы.
 * Для работы с текстовыми файлами необходимо реализовать функциональность записи (дозаписи) в файл.
 * Требуется определить исключения для каждого слоя приложения и корректно их обработать.
 *
 * @author Dmitriy Grigorev
 */
public class App {
    private ConsoleManager console = new ConsoleManager();

    private void run() {
        try {
            console.initMainLoop();
        } catch (ConsoleManagerException e) {
            System.out.println("Something wrong with the input!");
            e.printStackTrace();
        }
    }

    private void stop() {
        try {
            console.breakMainLoop();
        } catch (ConsoleManagerException e) {
            System.out.println("Something wrong with the input!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
