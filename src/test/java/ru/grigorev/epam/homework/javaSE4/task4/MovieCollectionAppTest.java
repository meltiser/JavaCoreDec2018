package ru.grigorev.epam.homework.javaSE4.task4;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.grigorev.epam.homework.javaSE4.task4.models.Actor;
import ru.grigorev.epam.homework.javaSE4.task4.models.Movie;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Dmitriy Grigorev
 */
class MovieCollectionAppTest {
    private MovieCollectionApp movieCollectionApp;
    private Actor tomHanks;
    private Actor leoDicaprio;

    @BeforeEach
    void setUp() {
        movieCollectionApp = new MovieCollectionApp();

        tomHanks = new Actor("Tom", "Hanks", LocalDate.of(1956, 7, 9));
        movieCollectionApp.addMovie("Forrest Gump", tomHanks, LocalDate.of(1994, 6, 23));
        movieCollectionApp.addMovie("The Green Mile", tomHanks, LocalDate.of(1999, 12, 10));

        leoDicaprio = new Actor("Leonardo", "DiCaprio", LocalDate.of(1974, 11, 11));
        movieCollectionApp.addMovie("Titanic", leoDicaprio, LocalDate.of(1997, 12, 19));
        movieCollectionApp.addMovie("Inception", leoDicaprio, LocalDate.of(2010, 7, 8));
    }

    @Test
    @SneakyThrows
    void testSaveLoad() {
        movieCollectionApp.setStorage(new File("test"));
        movieCollectionApp.saveToFile();

        MovieCollectionApp anotherApp = new MovieCollectionApp();
        anotherApp.setStorage(new File("test"));
        anotherApp.loadFromFile();

        assertEquals(movieCollectionApp.getMovies().size(), anotherApp.getMovies().size());

        Files.delete(Path.of("test"));
    }

    @Test
    void testDeleteMovie() {
        movieCollectionApp.deleteMovieByName("Titanic");
        assertEquals(3, movieCollectionApp.getMovies().size());
    }

    @Test
    void testActorsCount() {
        assertEquals(2, movieCollectionApp.getActors().size());
    }

    @Test
    void testGetActorByName() {
        Actor tom = movieCollectionApp.getActorByName("Tom", "Hanks").get();
        assertEquals(tomHanks, tom);
    }

    @Test
    void testGetAllMoviesByActor() {
        List<Movie> dicaprioMovies = movieCollectionApp.getAllMoviesByActor(leoDicaprio);
        assertEquals(2, dicaprioMovies.size());
    }
}