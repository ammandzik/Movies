package movie.service;

import org.junit.jupiter.api.Test;

import static movie.service.MovieService.*;
import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    private final String FILE_PATH = "src/test/resources/test.json";

    @Test
    void shouldSortMoviesByCategoryCorrectly() {


        //when

        var movies = assertDoesNotThrow(() -> movieByCategory("Thriller", FILE_PATH));
        var movieCategory = movies.get(0).getCategory().getDescription();

        //then

        assertEquals("Thriller", movieCategory);

    }

    @Test
    void shouldSortMovieByReleaseDateInAscendingOrder() {


        //when

        var movies = movieByReleaseDate(FILE_PATH);

        //then

        for (int i = 0; i < movies.size() - 1; i++) {

            assertTrue(movies.get(i).getReleaseDate() <= movies.get(i + 1).getReleaseDate());
        }

        for (int i = 0; i < movies.size() - 1; i++) {

            assertFalse(movies.get(i).getReleaseDate() > movies.get(i + 1).getReleaseDate());

        }

    }

    @Test
    void shouldSortMovieByRatingInDescendingOrder() {


        //when

        var movies = movieByRating(FILE_PATH);

        //then

        for (int i = 0; i < movies.size() - 1; i++) {

            assertTrue(movies.get(i).getRating() >= (movies.get(i + 1).getRating()));
        }

        for (int i = 0; i < movies.size() - 1; i++) {

            assertFalse(movies.get(i).getRating() < (movies.get(i + 1).getRating()));
        }

    }

    @Test
    void shouldCorrectlyReturnMovieRandomly() {

        //when

        var movie1 = assertDoesNotThrow(() -> getRandomTitle(FILE_PATH));
        var movie2 = assertDoesNotThrow(() -> getRandomTitle(FILE_PATH));

        //then

        assertNotNull(movie1);
        assertNotEquals(movie1, movie2);

    }
}
