package ru.grigorev.epam.homework.javaSE4.task4;

import lombok.Getter;
import lombok.Setter;
import ru.grigorev.epam.homework.javaSE4.task4.models.Actor;
import ru.grigorev.epam.homework.javaSE4.task4.models.Movie;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Дана коллекция фильмов, содержащая информацию об актерах,
 * снимавшихся в главных ролях (один актер мог сниматься и в нескольких фильмах).
 * Необходимо написать приложение, позволяющее при запуске восстанавливать коллекцию фильмов,
 * позволять ее модифицировать, а по завершении работы приложения – сохранять (в файл).
 * Для восстановления/сохранения коллекции использовать  сериализацию/десериализацию.
 *
 * @author Dmitriy Grigorev
 */
public class MovieCollectionApp {
    private static final String DEFAULT_STORAGE_NAME = "db";

    @Getter
    @Setter
    private File storage = new File(DEFAULT_STORAGE_NAME);
    @Getter
    private List<Movie> movies = new ArrayList<>();
    @Getter
    private List<Actor> actors = new ArrayList<>();

    public void saveToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(storage);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Movie movie : movies) {
            oos.writeObject(movie);
        }

        oos.close();
        fos.close();
    }

    public void loadFromFile() throws IOException, ClassNotFoundException {
        if (!storage.exists()) {
            return;
        }
        FileInputStream fis = new FileInputStream(storage);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (fis.available() > 0) {
            Movie readMovie = (Movie) ois.readObject();
            movies.add(readMovie);
            addActorIfNotExists(readMovie);
        }

        fis.close();
        ois.close();
    }

    public void addMovie(String name, Actor actor, LocalDate year) {
        Movie movieToAdd = new Movie(name, actor, year);
        movies.add(movieToAdd);
        addActorIfNotExists(movieToAdd);
    }

    private boolean isActorPresent(Actor actor) {
        for (Actor present : actors) {
            if (present.getFirstName().equalsIgnoreCase(actor.getFirstName())
                    && present.getLastName().equalsIgnoreCase(actor.getLastName())) {
                return true;
            }
        }
        return false;
    }

    private void addActorIfNotExists(Movie movieToAdd) {
        if (!isActorPresent(movieToAdd.getActor())) {
            actors.add(movieToAdd.getActor());
        }
    }

    public List<Movie> getAllMoviesByActor(Actor actor) {
        return movies.stream().filter((m) -> m.getActor().equals(actor)).collect(Collectors.toList());
    }

    public void deleteMovieByName(String name) {
        movies.removeIf(movie -> movie.getName().equalsIgnoreCase(name));
    }

    public void showAllMovies() {
        movies.forEach(System.out::println);
    }

    public void showAllActors() {
        actors.forEach(System.out::println);
    }

    public Optional<Actor> getActorByName(String firstName, String lastName) {
        for (Actor present : actors) {
            if (present.getFirstName().equalsIgnoreCase(firstName)
                    && present.getLastName().equalsIgnoreCase(lastName)) {
                return Optional.of(present);
            }
        }
        return Optional.empty();
    }
}
