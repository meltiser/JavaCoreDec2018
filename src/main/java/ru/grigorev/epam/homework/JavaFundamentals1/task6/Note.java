package ru.grigorev.epam.homework.JavaFundamentals1.task6;

import java.util.Objects;

/**
 * Class that is emulating notes for notebook. Simply contains String value inside.
 * Notes are case-sensitive.
 *
 * @author Dmitriy Grigorev
 */
public class Note {
    /**
     * String value in note. Can't be empty.
     */
    private String content;

    public Note(String content) {
        if (content.isEmpty()) return;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content.isEmpty()) return;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    /**
     * Default Intellij Idea realization of equals() and hashCode().
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note1 = (Note) o;
        return Objects.equals(content, note1.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
