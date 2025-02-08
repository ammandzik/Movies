package movie.service;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static movie.service.FileService.convertFileToJson;

class FileServiceTest {

    private static final String VALID_FILE_PATH = "src/test/resources/test.json";
    private static final String EMPTY_FILE_PATH = "src/test/resources/empty.json";
    private static final String INVALID_FILE_PATH = "src/test/resources/invalid.json";
    private static final String NON_EXISTENT_FILE_PATH = "src/test/resources/nonexistent.json";

    @Test
    void shouldConvertFileToJsonCorrectly() {
        // when
        JSONArray jsonArray = assertDoesNotThrow(() -> convertFileToJson(VALID_FILE_PATH));

        // then
        assertNotNull(jsonArray, "JSON array should not be null");
        assertFalse(jsonArray.isEmpty(), "JSON array should not be empty");

        System.out.println("JSON array size: " + jsonArray.size());

        assertFalse(jsonArray.isEmpty(), "JSON array should have at least one element");
    }

    @Test
    void convertEmptyJsonShouldReturnEmptyJSONArray() {
        // when
        JSONArray jsonArray = assertDoesNotThrow(() -> convertFileToJson(EMPTY_FILE_PATH));

        // then
        assertNotNull(jsonArray, "JSON array should not be null");
        assertTrue(jsonArray.isEmpty(), "JSON array should be empty for an empty file");
    }

    @Test
    void convertInvalidJsonShouldThrowFileParseException() {
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> convertFileToJson(INVALID_FILE_PATH));

        // then
        assertNotNull(exception.getMessage(), "Exception message should not be null");
        assertTrue(exception.getMessage().contains("File does not exist: " + INVALID_FILE_PATH),
                "Exception message should indicate parsing error with the correct file name");
    }


    @Test
    void convertNonExistentFileShouldThrowIllegalArgumentException() {
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> convertFileToJson(NON_EXISTENT_FILE_PATH));

        // then
        assertEquals("File does not exist: " + NON_EXISTENT_FILE_PATH, exception.getMessage());
    }
}