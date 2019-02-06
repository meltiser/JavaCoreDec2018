package ru.grigorev.epam.homework.JavaSE3.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dmitriy Grigorev
 */
class CrazyLoggerTest {

    @BeforeEach
    void setUp() {
        System.out.println("---------------------------");
    }

    @Test
    void logToJournalTest() {
        CrazyLogger crazyLogger = new CrazyLogger(CrazyLoggerTest.class);
        crazyLogger.log(CrazyLogger.Level.TRACE, "Hi!");
        String actual = crazyLogger.getLogJournal().toString();
        assertTrue(actual.startsWith(CrazyLogger.Level.TRACE.toString()));
        assertTrue(actual.endsWith("Hi!\n"));
    }

    @Test
    void writeLogToFileTest() throws IOException {
        CrazyLogger crazyLogger = new CrazyLogger(CrazyLoggerTest.class);
        crazyLogger.log(CrazyLogger.Level.WARN, "Shouldn't be written");
        crazyLogger.log(CrazyLogger.Level.DEBUG, "HELLO!");
        crazyLogger.writeLogToFile(CrazyLogger.Level.DEBUG);

        String logFileName = LocalDate.now() + " log.txt";
        String actual = Files.readString(Path.of(logFileName));
        assertTrue(actual.startsWith(CrazyLogger.Level.DEBUG.toString()));
        assertTrue(actual.endsWith("HELLO!\n"));

        Files.delete(Path.of(logFileName));
    }

    @Test
    void showLogTest() {
        CrazyLogger crazyLogger = new CrazyLogger(CrazyLoggerTest.class);
        crazyLogger.log(CrazyLogger.Level.WARN, "It's gonna break...");
        crazyLogger.log(CrazyLogger.Level.ERROR, "Told you");
        crazyLogger.log(CrazyLogger.Level.FATAL, "Ask senior");
        System.out.println("*************");
        crazyLogger.showLog(CrazyLogger.Level.ALL);
    }

    @Test
    void writeToOutputStreamTest() throws IOException {
        CrazyLogger crazyLogger = new CrazyLogger(CrazyLoggerTest.class);
        crazyLogger.log(CrazyLogger.Level.ERROR, "CRITICAL ERROR!");
        crazyLogger.log(CrazyLogger.Level.INFO, "Something happened");
        crazyLogger.writeToOutputStream(CrazyLogger.Level.ALL, new FileOutputStream("1.txt", true));

        String logFileName = "1.txt";
        String actual = Files.readString(Path.of(logFileName));
        assertTrue(actual.startsWith(CrazyLogger.Level.ERROR.toString()));
        assertTrue(actual.endsWith("Something happened\n"));

        Files.delete(Path.of(logFileName));
    }
}