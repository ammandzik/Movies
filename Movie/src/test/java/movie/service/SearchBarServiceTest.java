package movie.service;

import movie.model.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static movie.service.SearchBarService.searchForTitle;
import static org.junit.jupiter.api.Assertions.*;

class SearchBarServiceTest {

    private final String FILE_PATH = "src/test/resources/test.json";

    @Test
    void shouldPrintAllFoundTitlesThatContainSearchPhrase() {

        //given
        final var PHRASE = "cos";

        //when
        List<Movie> movies = assertDoesNotThrow(() -> searchForTitle(PHRASE, FILE_PATH));

        //then
        assertFalse(movies.isEmpty());
        assertTrue(movies.size() > 0);


    }

    @Test
    void shouldPrintErrorMessageWhileSearchResultsAreEmpty() {

        //given
        String PHRASE = "Freepik";


        //when
        List<Movie> movies = assertDoesNotThrow(() -> searchForTitle(PHRASE, FILE_PATH));

        //then
        assertTrue(movies.isEmpty());
        assertEquals(0, movies.size());


    }
}
