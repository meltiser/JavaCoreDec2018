package ru.grigorev.epam.homework.javaSE5.task1;

import ru.grigorev.epam.homework.javaSE5.task1.exceptions.ConsoleManagerException;
import ru.grigorev.epam.homework.javaSE5.task1.exceptions.FileHandlerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Dmitriy Grigorev
 */
public class ConsoleManager {
    private FileHandler fileHandler = new FileHandlerImpl();

    private String currentDirectory = System.getProperty("user.dir") + "\\";
    private BufferedReader input;
    private boolean isRunning = true;


    public void initMainLoop() throws ConsoleManagerException {
        input = new BufferedReader(new InputStreamReader(System.in));
        println("Type \"help\" to see list of commands");
        printCurrentDirectory();
        try {
            while (isRunning) {
                String s = input.readLine();
                processInput(s);
            }
        } catch (IOException e) {
            throw new ConsoleManagerException(e);
        }
    }

    public void breakMainLoop() throws ConsoleManagerException {
        isRunning = false;
        try {
            input.close();
        } catch (IOException e) {
            throw new ConsoleManagerException(e);
        }
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }

    private void printCurrentDirectory() {
        print(currentDirectory);
    }

    private void processInput(String s) throws ConsoleManagerException {
        String[] words = s.split("\\s+");
        if (s.equalsIgnoreCase(Commands.EXIT)) {
            breakMainLoop();
            return;
        }
        if (s.equalsIgnoreCase(Commands.DIR)) {
            showCurrentFolderContent();
        }
        if (s.equalsIgnoreCase(Commands.HELP)) {
            showHelp();
        }

        if (s.startsWith(Commands.CD)) {
            changeDirectory(words[1]);
        }
        if (s.startsWith(Commands.CREATE)) {
            createFile(words[1]);
        }
        if (s.startsWith(Commands.OPEN)) {
            openFile(words[1]);
        }
        if (s.startsWith(Commands.DELETE)) {
            deleteFile(words[1]);
        }
        if (s.startsWith(Commands.EDIT)) {
            editFile(words[1]);
        }
        printCurrentDirectory();
    }

    private void showHelp() {
        StringBuilder helpSb = new StringBuilder();
        helpSb.append(String.format("%s%n", "Commands:"))
                .append(String.format("%-10s%s%n", Commands.CD, "changes directory"))
                .append(String.format("%s%-7s%s%n", Commands.CD, Commands.DIRECTORY_UP, "go one level up"))
                .append(String.format("%-10s%s%n", Commands.DIR, "shows a list of files and subfolders"))
                .append(String.format("%-10s%s%n", Commands.EXIT, "exits app"))
                .append(String.format("%-10s%s%n", Commands.OPEN, "opens file in default desktop program"))
                .append(String.format("%-10s%s%n", Commands.CREATE, "creates file"))
                .append(String.format("%-10s%s%n", Commands.DELETE, "deletes file"))
                .append(String.format("%-10s%s%n", Commands.EDIT, "edits file (opens file editor input)"))
                .append(String.format("%-10s%s%n", Commands.COMMIT, "(editing) finishes editing and commits changes"))
                .append(String.format("%-10s%s%n", Commands.CANCEL, "(editing) finishes editing and cancels changes"))
                .append(String.format("%-10s%s%n", Commands.HELP, "shows help"));
        print(helpSb.toString());
    }

    private void editFile(String fileName) throws ConsoleManagerException {
        try {
            String txtContent = fileHandler.getTxtContent(currentDirectory + fileName);
            println("--------Editing: " + fileName + " (type \"commit\" to finish editing or \"cancel\")--------");
            print(txtContent);
            String fileInput = getFileInput();
            fileHandler.editTxtFile(currentDirectory + fileName, fileInput);
            println("--------Editing finished--------");
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void deleteFile(String fileName) {
        try {
            fileHandler.deleteFile(currentDirectory + fileName);
            println("File " + fileName + " deleted!");
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void openFile(String fileName) {
        try {
            fileHandler.openFile(currentDirectory + fileName);
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void createFile(String fileName) {
        try {
            fileHandler.createFile(currentDirectory + fileName);
            println("File " + fileName + " created!");
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void changeDirectory(String directoryTo) {
        try {
            currentDirectory = fileHandler.changeDirectory(currentDirectory, directoryTo);
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    /**
     * Used for get input for editing file.
     *
     * @return
     * @throws ConsoleManagerException
     */
    private String getFileInput() throws ConsoleManagerException {
        StringBuilder sb = new StringBuilder();
        String toFile = "";
        while (true) {
            try {
                toFile = input.readLine();
            } catch (IOException e) {
                throw new ConsoleManagerException(e);
            }

            if (toFile.equalsIgnoreCase(Commands.CANCEL)) {
                sb.setLength(0);
                break;
            }

            if (toFile.equalsIgnoreCase(Commands.COMMIT)) {
                break;
            }
            sb.append(toFile).append(System.lineSeparator());
        }
        return sb.toString();
    }

    private void showCurrentFolderContent() {
        try {
            println(fileHandler.getCurrentFolderContent(currentDirectory));
        } catch (FileHandlerException e) {
            printException(e);
        }
    }
}
