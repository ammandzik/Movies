package movie.service;

import movie.model.Movie;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import static movie.service.FileService.convertFileToJson;
import static movie.service.FileService.jsonFileToObjectList;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private final String FILE_PATH = "src/test/resources/test.json";


    @Test
    public void shouldConvertFileToJsonCorrectly() {

        //given

        String test = FILE_PATH;

        //when

        var jsonArray = assertDoesNotThrow(() -> convertFileToJson(test));

        //then


        assertEquals(jsonArray.getClass(), JSONArray.class);


    }

    @Test
    public void convertEmptyJsonTestShouldThrowException() {

        // given

        final String EMPTY_FILE = "src/test/resources/empty.json";


        // when

        var exception = assertThrows(RuntimeException.class, () -> convertFileToJson(EMPTY_FILE));

        // then

        assertEquals("Could not parse file.", exception.getMessage());

    }

    @Test
    public void shouldProvideCorrectJsonFileConversionToObjectList() {

        // given

        String test = FILE_PATH;

        // when

        var objects = assertDoesNotThrow(() -> jsonFileToObjectList(test, Movie.class));

        // then

        assertNotNull(objects.get(0));

    }

    @Test
    public void jsonFileToObjectListShouldThrowIndexOutOfBoundsException() {


        // when

        var objects = jsonFileToObjectList(FILE_PATH, Movie.class);


        // then

        assertThrows(IndexOutOfBoundsException.class, () -> objects.get(objects.size()));


    }


}
