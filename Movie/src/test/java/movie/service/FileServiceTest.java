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
    void shouldConvertFileToJsonCorrectly() {


        //when

        var jsonArray = assertDoesNotThrow(() -> convertFileToJson(FILE_PATH));

        //then


        assertEquals(jsonArray.getClass(), JSONArray.class);


    }

    @Test
    void convertEmptyJsonTestShouldThrowException() {

        // given

        final String EMPTY_FILE = "src/test/resources/empty.json";


        // when

        var exception = assertThrows(RuntimeException.class, () -> convertFileToJson(EMPTY_FILE));

        // then

        assertEquals("Could not parse file.", exception.getMessage());

    }

    @Test
    void shouldProvideCorrectJsonFileConversionToObjectList() {


        // when

        var objects = assertDoesNotThrow(() -> jsonFileToObjectList(FILE_PATH, Movie.class));

        // then

        assertNotNull(objects.get(0));

    }

    @Test
    void jsonFileToObjectListShouldThrowIndexOutOfBoundsException() {


        // when

        var objects = jsonFileToObjectList(FILE_PATH, Movie.class);
        var objectsSize = objects.size();

        // then

        assertThrows(IndexOutOfBoundsException.class, () -> objects.get(objectsSize));


    }


}
