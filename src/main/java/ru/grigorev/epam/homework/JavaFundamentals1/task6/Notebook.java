package ru.grigorev.epam.homework.JavaFundamentals1.task6;

import java.util.Arrays;

/**
 * This class is emulating notes in notebook. It provides adding, editing, deleting and showing all elements.
 * Notes are stored in simple array, which overwrites if number of notes becomes bigger than array size.
 * By adding a new note, it inserts on empty place (null link), so if there was deleted note before one that adds
 * it will be placed on empty link. Because of this behavior - order is not guaranteed.
 *
 * @author Dmitriy Grigorev
 */
public class Notebook {
    /**
     * Array that contains notes. Default capacity is 10.
     */
    private Note[] notes = new Note[10];

    /**
     * The size of the Notebook (notes it contains).
     */
    private int size;

    /**
     * Adds new note to notebook on empty link. If size is bigger than array length,
     * method creates new array that twice bigger and copies values to it.
     *
     * @param note Note object with String content in it. Can't be null
     */
    public void addNote(Note note) {
        if (note.getContent() == null) return;
        size++;
        if (size > notes.length) {
            notes = Arrays.copyOfRange(notes, 0, notes.length * 2);
        }
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] == null) {
                notes[i] = note;
                return;
            }
        }
    }

    /**
     * Deletes note in notebook if exists.
     *
     * @param note Note object with String content in it. Can't be null
     * @throws NoSuchNoteException if note is absent
     */
    public void deleteNote(Note note) {
        for (int i = 0; i < size; i++) {
            if (notes[i].equals(note)) {
                notes[i] = null;
                size--;
                return;
            }
        }
        throw new NoSuchNoteException();
    }

    /**
     * Prints all notes in notebook with order it placed in array.
     */
    public void showAllNotes() {
        System.out.println("---------------------------");
        for (Note note : notes) {
            if (note == null) {
                continue;
            }
            System.out.println(note);
        }
    }

    /**
     * Edits note if exists.
     *
     * @param editNote Note object needs to be edited
     * @param edited   String value - edited text
     * @throws NoSuchNoteException if note is absent
     */
    public void editNote(Note editNote, String edited) {
        for (Note note : notes) {
            if (note == null) {
                continue;
            }
            if (note.equals(editNote)) {
                note.setContent(edited);
                return;
            }
        }
        throw new NoSuchNoteException();
    }

    /**
     * @param note Note object needs to checked
     * @return true if note is in notebook, false - otherwise
     */
    public boolean contains(Note note) {
        for (Note n : notes) {
            if (n == null) {
                continue;
            }
            if (n.equals(note)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return size of notebook (actual number of elements)
     */
    public int size() {
        return size;
    }
}
