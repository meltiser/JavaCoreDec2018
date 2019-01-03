package ru.grigorev.epam.homework.JavaFundamentals1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.grigorev.epam.homework.JavaFundamentals1.task6.NoSuchNoteException;
import ru.grigorev.epam.homework.JavaFundamentals1.task6.Note;
import ru.grigorev.epam.homework.JavaFundamentals1.task6.Notebook;

/**
 * @author Dmitriy Grigorev
 */
public class Task6Test {
    private Notebook notebook = new Notebook();

    @Test
    void deleteNoteTestExc() {
        Assertions.assertThrows(NoSuchNoteException.class, () -> notebook.deleteNote(new Note("NO")));
    }

    @Test
    void editNoteTestExc() {
        Assertions.assertThrows(NoSuchNoteException.class, () -> notebook.editNote(new Note("NO"), "NONO"));
    }

    @Test
    void sizeTest() {
        notebook.addNote(new Note("TEST1"));
        notebook.addNote(new Note("TEST2"));
        notebook.addNote(new Note("TEST3"));
        int result = notebook.size();
        Assertions.assertEquals(3, result);
    }

    @Test
    void addNoteTest() {
        notebook.addNote(new Note("TEST"));
        boolean result = notebook.contains(new Note("TEST"));
        Assertions.assertTrue(result);
    }

    @Test
    void deleteNoteTest() {
        notebook.addNote(new Note("TEST"));
        notebook.deleteNote(new Note("TEST"));
        boolean result = notebook.contains(new Note("TEST"));
        Assertions.assertFalse(result);
    }

    @Test
    void editNoteTest() {
        notebook.addNote(new Note("TEST"));
        notebook.editNote(new Note("TEST"), "EDIT");
        boolean result = notebook.contains(new Note("EDIT"));
        Assertions.assertTrue(result);
    }

    @Test
    void testCase() {
        notebook.addNote(new Note("ONE"));
        notebook.addNote(new Note("TWO"));
        notebook.addNote(new Note("THREE"));

        notebook.deleteNote(new Note("TWO"));

        notebook.editNote(new Note("ONE"), "FOUR");

        notebook.deleteNote(new Note("FOUR"));

        notebook.addNote(new Note("FIVE"));
        notebook.addNote(new Note("SIX"));

        notebook.editNote(new Note("THREE"), "SEVEN");

        notebook.deleteNote(new Note("FIVE"));

        notebook.showAllNotes();
        int result = notebook.size();
        Assertions.assertEquals(2, result);
    }
}
