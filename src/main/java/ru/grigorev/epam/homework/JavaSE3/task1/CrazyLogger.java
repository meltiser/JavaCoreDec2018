package ru.grigorev.epam.homework.JavaSE3.task1;

import lombok.Getter;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Необходимо создать класс CrazyLogger, ведущий журнал лога, используя как накопитель
 * объект типа StringBuilder {@link CrazyLogger#logJournal}.
 * Логгер должен содержать методы поиска определенной информации
 * в таком логе [с возможностью вывода ее в поток ввода вывода]
 * {@link CrazyLogger#writeToOutputStream(Level, OutputStream)}.
 * Информацию логгер хранит в форматированном виде: dd-mm-YYYY : hh-mm – message {@link CrazyLogger#DATE_PATTERN}.
 *
 * @author Dmitriy Grigorev
 */
public class CrazyLogger {
    private static final String DATE_PATTERN = "dd-MM-YYYY : HH-mm";

    @Getter
    private StringBuilder logJournal;
    private StringBuilder buffer;
    private Class clazzForLog;

    public enum Level {
        ALL,
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }

    public CrazyLogger(Class clazzForLog) {
        this.clazzForLog = clazzForLog;
        logJournal = new StringBuilder();
        buffer = new StringBuilder();
    }

    /**
     * Adds message (with logging level and current time) to log journal and prints it to console.
     *
     * @param level of logging
     * @param msg   to log
     */
    public void log(Level level, String msg) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        // that kind of string: "DEBUG CrazyLogger 05-02-2019 : 22-45 - msg"
        buffer.append(level)
                .append(" ")
                .append(clazzForLog.getSimpleName())
                .append(" ")
                .append(date.format(formatter))
                .append(" - ")
                .append(msg)
                .append("\n");
        System.out.print(buffer);
        logJournal.append(buffer);
        buffer.setLength(0);
    }

    /**
     * Shows logs to console
     *
     * @param level of logging
     */
    public void showLog(Level level) {
        if (level == null) return;

        String[] logs = logJournal.toString().split("\n");
        Arrays.stream(logs)
                .filter(log -> log.startsWith(level.toString()) || level.equals(Level.ALL))
                .forEach(log -> System.out.print(log + "\n"));
    }

    /**
     * Creates file "{@link CrazyLogger#DATE_PATTERN} log.txt" with logging level messages.
     *
     * @param level of logging
     * @throws IOException
     */
    public void writeLogToFile(Level level) throws IOException {
        if (level == null) return;

        String logFileName = LocalDate.now() + " log.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(logFileName, false));

        writeLogsToBufferedWriter(level, bw);
    }

    /**
     * Writes log to output stream. Wraps inside to {@link BufferedWriter}
     *
     * @param level        of logging
     * @param outputStream could be any output stream (file, network or even {@link System#out})
     * @throws IOException
     */
    public void writeToOutputStream(Level level, OutputStream outputStream) throws IOException {
        if (level == null || outputStream == null) return;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));

        writeLogsToBufferedWriter(level, bw);
    }

    /**
     * Inner method. Checks and writes needed level of logging.
     *
     * @param level of logging
     * @param bw    {@link BufferedWriter} object
     * @throws IOException
     */
    private void writeLogsToBufferedWriter(Level level, BufferedWriter bw) throws IOException {
        String[] logs = logJournal.toString().split("\n");
        Arrays.stream(logs)
                .filter(log -> log.startsWith(level.toString()) || level.equals(Level.ALL))
                .forEach(log -> writeUnchecked(bw, log + "\n"));

        bw.flush();
        bw.close();
    }

    /**
     * Uses method {@link BufferedWriter#write(String)} but throws an unchecked exception.
     * Needs for Stream API to make code more concise.
     *
     * @param bw BufferedWriter object
     * @param s  String to write
     * @see <a href="https://www.oreilly.com/ideas/handling-checked-exceptions-in-java-streams">
     * Handling checked exceptions in Java streams
     * </a>
     */
    private void writeUnchecked(BufferedWriter bw, String s) {
        try {
            bw.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
