package ru.grigorev.epam.homework.javaSE5.task1;

import ru.grigorev.epam.homework.javaSE5.task1.exceptions.FileHandlerException;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitriy Grigorev
 */
public class FileHandlerImpl implements FileHandler {

    @Override
    public void openFile(String absoluteDir) throws FileHandlerException {
        Path path = Path.of(absoluteDir);
        if (Files.isDirectory(path)) {
            throw new FileHandlerException("Path is directory!");
        }

        if (!Desktop.isDesktopSupported()) {
            throw new FileHandlerException("Desktop is not supported");
        }

        Desktop desktop = Desktop.getDesktop();
        if (path.toFile().exists()) {
            try {
                desktop.open(path.toFile());
            } catch (IOException e) {
                throw new FileHandlerException("Can't open file", e);
            }
        } else {
            throw new FileHandlerException("File doesn't not exist!");
        }
    }

    @Override
    public void createFile(String absoluteDir) throws FileHandlerException {
        Path path = Path.of(absoluteDir);
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new FileHandlerException("Cant't create file", e);
        }
    }

    @Override
    public void deleteFile(String absoluteDir) throws FileHandlerException {
        Path path = Path.of(absoluteDir);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new FileHandlerException("Can't delete file", e);
        }
    }

    @Override
    public String getTxtContent(String absoluteDir) throws FileHandlerException {
        StringBuilder buffer = new StringBuilder();
        Path path = Path.of(absoluteDir);

        if (!absoluteDir.endsWith(".txt")) {
            throw new FileHandlerException("The app can read only .txt files!");
        }

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(str -> buffer.append(str).append(System.lineSeparator()));
        } catch (IOException e) {
            throw new FileHandlerException("Can't read file!", e);
        }

        return buffer.toString();
    }

    @Override
    public void editTxtFile(String absoluteDir, String dataToWrite) throws FileHandlerException {
        if (dataToWrite.isEmpty()) return;

        Path path = Path.of(absoluteDir);
        if (!absoluteDir.endsWith(".txt")) {
            throw new FileHandlerException("The app can edit only .txt files!");
        }

        try (FileWriter fileWriter = new FileWriter(path.toFile(), true)) {
            fileWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new FileHandlerException("Can't edit file", e);
        }
    }

    @Override
    public String getCurrentFolderContent(String absoluteDir) throws FileHandlerException {
        String content = "";
        try {
            content = Files.list(Path.of(absoluteDir))
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new FileHandlerException("Can't get file content!", e);
        }
        return content;
    }

    @Override
    public String changeDirectory(String currentDirectory, String directoryTo) throws FileHandlerException {
        Path path = Path.of(currentDirectory + directoryTo);
        if (!path.toFile().exists()) {
            throw new FileHandlerException("Directory doesn't exist!");
        }

        if (directoryTo.equals(Commands.DIRECTORY_UP)) {
            if (currentDirectory.length() == 3) {
                return currentDirectory;
            }
            String dirWithoutSlash = currentDirectory.substring(0, currentDirectory.length() - 1);
            //one level up directory
            return dirWithoutSlash.substring(0, dirWithoutSlash.lastIndexOf('\\') + 1);
        }

        if (Files.isDirectory(path)) {
            return currentDirectory + directoryTo + "\\";
        } else {
            throw new FileHandlerException("Input directory is a file!");
        }
    }
}
