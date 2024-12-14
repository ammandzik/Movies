package movie.service;

import movie.model.Movie;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private final String FILE_PATH = "src/test/java/resources/test.json";


    @Test
    public void convertFileToJsonTest() {

        //given

        String test = FILE_PATH;

        //when

        JSONArray jsonArray = FileService.convertFileToJson(test);

        //then

        assertDoesNotThrow(() -> FileService.convertFileToJson(test));
        assertEquals(jsonArray.getClass(), JSONArray.class);


    }

    @Test
    public void convertEmptyJsonTest() {

        // given

        final String EMPTY_FILE = "src/test/java/resources/empty.json";


        // when

        var exception = assertThrows(RuntimeException.class, () -> FileService.convertFileToJson(EMPTY_FILE));

        // then

        assertEquals("Could not parse file.", exception.getMessage());

    }

    @Test
    public void jsonFileToObjectListTest() {

        // given

        String test = FILE_PATH;

        // when

        var objects = FileService.jsonFileToObjectList(test, Movie.class);

        // then

        assertDoesNotThrow(() -> FileService.jsonFileToObjectList(test, Movie.class));
        assertNotNull(objects.get(0));


    }

    @Test
    public void jsonFileToObjectListShouldThrowIndexOutOfBoundsException() {

        // given

        String test = FILE_PATH;

        // when

        var objects = FileService.jsonFileToObjectList(test, Movie.class);

        // then

        assertThrows(IndexOutOfBoundsException.class, () -> objects.get(2));


    }


}
