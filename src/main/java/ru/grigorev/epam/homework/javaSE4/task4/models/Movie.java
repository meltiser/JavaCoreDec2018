package ru.grigorev.epam.homework.javaSE4.task4.models;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Dmitriy Grigorev
 */
@Data
public class Movie implements Serializable {
    private String name;
    private Actor actor;
    private LocalDate year;

    public Movie(String name, Actor actor, LocalDate year) {
        this.name = name;
        this.actor = actor;
        this.year = year;

        actor.addMovie(this);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY");
        return String.format("%s(%s) Starring %s %s",
                name,
                formatter.format(year),
                actor.getFirstName(),
                actor.getLastName());
    }
}
