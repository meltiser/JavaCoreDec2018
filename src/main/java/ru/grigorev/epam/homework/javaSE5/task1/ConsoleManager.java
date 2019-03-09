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
    private FilesHandler filesHandler = new FilesHandlerImpl();

    private String currentDirectory = System.getProperty("user.dir") + "\\";
    private BufferedReader input;
    private boolean isRunning = true;


    public void initMainLoop() throws ConsoleManagerException {
        input = new BufferedReader(new InputStreamReader(System.in));
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
        //TODO
    }

    private void editFile(String fileName) throws ConsoleManagerException {
        try {
            String txtContent = filesHandler.getTxtContent(currentDirectory + fileName);
            println("--------Editing: " + fileName + " (type \"commit\" to finish editing or \"cancel\")--------");
            print(txtContent);
            String fileInput = getFileInput();
            filesHandler.editTxtFile(currentDirectory + fileName, fileInput);
            println("--------Editing finished--------");
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void deleteFile(String fileName) {
        try {
            filesHandler.deleteFile(currentDirectory + fileName);
            println("File " + fileName + " deleted!");
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void openFile(String fileName) {
        try {
            filesHandler.openFile(currentDirectory + fileName);
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void createFile(String fileName) {
        try {
            filesHandler.createFile(currentDirectory + fileName);
            println("File " + fileName + " created!");
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

    private void changeDirectory(String directoryTo) {
        try {
            currentDirectory = filesHandler.changeDirectory(currentDirectory, directoryTo);
        } catch (FileHandlerException e) {
            printException(e);
        }
    }

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
            println(filesHandler.getCurrentFolderContent(currentDirectory));
        } catch (FileHandlerException e) {
            printException(e);
        }
    }
}
