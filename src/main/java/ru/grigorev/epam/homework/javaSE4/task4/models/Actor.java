package ru.grigorev.epam.homework.javaSE4.task4.models;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Grigorev
 */
@Data
public class Actor implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate born;
    private List<Movie> movies = new ArrayList<>();

    public Actor(String firstName, String lastName, LocalDate born) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.born = born;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        String s = String.format("%s %s. Born in %s. Starred in ",
                firstName,
                lastName,
                formatter.format(born));

        StringBuilder sb = new StringBuilder(s);

        for (Movie movie : movies) {
            sb.append(movie.getName()).append(", ");
        }
        return sb.substring(0, sb.length() - 2); // removes last ", "
    }
}
