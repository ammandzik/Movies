package movie.service;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private final String FILE_PATH = "src/test/java/resources/test.json";
    private final String EMPTY_FILE = "src/test/java/resources/empty.json";


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

        String emptyFile = EMPTY_FILE;

        // when

        var exception = assertThrows(RuntimeException.class, () -> FileService.convertFileToJson(emptyFile));

        // then

        assertEquals("Could not parse file.", exception.getMessage());

    }

    @Test
    public void jsonFileToObjectListTest() {

        // given

        String test = FILE_PATH;

        // when

        var objects = FileService.jsonFileToObjectList(test);

        // then

        assertDoesNotThrow(() -> FileService.jsonFileToObjectList(test));
        assertNotNull(objects.get(0));


    }

    @Test
    public void jsonFileToObjectListShouldThrowIndexOutOfBoundsException() {

        // given

        String test = FILE_PATH;

        // when

        var objects = FileService.jsonFileToObjectList(test);

        // then

        assertThrows(IndexOutOfBoundsException.class, () -> objects.get(2));


    }


}
