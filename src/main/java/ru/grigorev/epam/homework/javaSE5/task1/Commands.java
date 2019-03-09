package ru.grigorev.epam.homework.javaSE5.task1;

/**
 * @author Dmitriy Grigorev
 */
public class Commands {
    public static final String DIRECTORY_UP = ".."; //command to get one level up. Used with "cd".
    public static final String EXIT = "exit"; //exit the app
    public static final String DIR = "dir"; //show file and directory names in current folder
    public static final String HELP = "help"; // show help

    //used for editing file
    public static final String CANCEL = "cancel"; //cancel file editing end exit without save
    public static final String COMMIT = "commit"; //finish file editing

    //starts with
    public static final String CD = "cd "; // change directory
    public static final String EDIT = "edit "; //start editing the file
    public static final String OPEN = "open "; // open file
    public static final String CREATE = "create "; // create file
    public static final String DELETE = "delete "; // delete file
}
