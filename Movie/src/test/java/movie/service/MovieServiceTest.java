package movie.service;

import org.junit.jupiter.api.Test;

import static movie.service.MovieService.*;
import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    private final String FILE_PATH = "src/test/resources/test.json";

    @Test
    void shouldSortMoviesByCategoryCorrectly() {
        // when
        var movies = assertDoesNotThrow(() -> movieByCategory("Thriller", FILE_PATH));

        // then
        assertFalse(movies.isEmpty(), "Movies list should not be empty");
        for (var movie : movies) {
            assertEquals("Thriller", movie.getCategory().getDescription(),
                    "All movies should have category 'Thriller'");
        }
    }

    @Test
    void shouldSortMovieByReleaseDateInAscendingOrder() {
        // when
        var movies = assertDoesNotThrow(() -> movieByReleaseDate(FILE_PATH));

        // then
        assertFalse(movies.isEmpty(), "Movies list should not be empty");
        for (int i = 0; i < movies.size() - 1; i++) {
            assertTrue(movies.get(i).getReleaseDate() <= movies.get(i + 1).getReleaseDate(),
                    "Movies should be sorted by release date in ascending order");
        }
    }

    @Test
    void shouldSortMovieByRatingInDescendingOrder() {
        // when
        var movies = assertDoesNotThrow(() -> movieByRating(FILE_PATH));

        // then
        assertFalse(movies.isEmpty(), "Movies list should not be empty");
        for (int i = 0; i < movies.size() - 1; i++) {
            assertTrue(movies.get(i).getRating() >= movies.get(i + 1).getRating(),
                    "Movies should be sorted by rating in descending order");
        }
    }

    @Test
    void shouldCorrectlyReturnMovieRandomly() {
        // when
        var movie1 = assertDoesNotThrow(() -> getRandomTitle(FILE_PATH));
        var movie2 = assertDoesNotThrow(() -> getRandomTitle(FILE_PATH));

        // then
        assertNotNull(movie1, "First random movie should not be null");
        assertNotNull(movie2, "Second random movie should not be null");

        // Check if movies are valid
        assertFalse(movie1.isBlank(), "First random movie title should not be blank");
        assertFalse(movie2.isBlank(), "Second random movie title should not be blank");
    }
}