package movie.service;

import movie.model.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static movie.service.SearchBarService.displaySearchResult;
import static org.junit.jupiter.api.Assertions.*;

class SearchBarServiceTest {

    private final String FILE_PATH = "src/test/resources/test.json";

    @Test
    void shouldPrintAllFoundTitlesThatContainSearchPhrase() {

        //given
        final var PHRASE = "cos";

        //when
        List<Movie> movies = assertDoesNotThrow(() -> displaySearchResult(PHRASE, FILE_PATH));

        //then
        assertFalse(movies.isEmpty());


    }

    @Test
    void shouldPrintErrorMessageWhileSearchResultsAreEmpty() {

        //given
        final var PHRASE = "Freepik";


        //when
        List<Movie> movies = assertDoesNotThrow(() -> displaySearchResult(PHRASE, FILE_PATH));

        //then
        assertTrue(movies.isEmpty());


    }
}
