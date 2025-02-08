package movie.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import movie.advice.FileParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The FileService class provides utility methods for working with JSON files.
 * It includes methods to convert JSON files to JSON arrays and to map JSON data to objects.
 * This class is designed as a utility and cannot be instantiated.
 */
public class FileService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

    private FileService() {}

    /**
     * Converts a JSON file to a {@link JSONArray}.
     * If the file is empty, it returns an empty {@link JSONArray}.
     *
     * @param fileName the path to the JSON file
     * @return a {@link JSONArray} representation of the file content
     * @throws IllegalArgumentException if the file does not exist
     * @throws RuntimeException         if there is an error reading or parsing the file
     */

    public static JSONArray convertFileToJson(String fileName) {
        validateFileExists(fileName);

        try (FileReader fileReader = new FileReader(fileName)) {
            return parseJsonArray(fileReader);
        } catch (IOException e) {
            throw new FileParseException("Error parsing the JSON file: " + fileName, e);
        }
    }

    /**
     * Validates that the file exists at the given path.
     *
     * @param fileName the path to the file
     * @throws IllegalArgumentException if the file does not exist
     */
    private static void validateFileExists(String fileName) {
        if (!Files.exists(Paths.get(fileName))) {
            throw new IllegalArgumentException("File does not exist: " + fileName);
        }
    }


    /**
     * Parses the content of a file into a {@link JSONArray}.
     * Returns an empty {@link JSONArray} for an empty file.
     *
     * @param fileReader the {@link FileReader} for reading the file
     * @return a {@link JSONArray} representation of the file content
     * @throws RuntimeException if the file contains invalid JSON
     */
    private static JSONArray parseJsonArray(FileReader fileReader) {
        try {
            JSONParser jsonParser = new JSONParser();
            Object parsedObject = jsonParser.parse(fileReader);

            if (parsedObject instanceof JSONArray jsonArray) {
                return jsonArray;
            } else {
                throw new IllegalArgumentException("File does not contain a valid JSON array");
            }
        } catch (ParseException e) {
            if (e.getPosition() == 0) {
                return new JSONArray();
            }
            throw new FileParseException("Error parsing the JSON file", e);
        } catch (IOException e) {
            throw new FileParseException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSONArray from a JSON file into a list of objects of a specified class.
     *
     * @param file       the path to the JSON file
     * @param targetClass the target class to map the JSON objects to
     * @param <T>        the type of the target class
     * @return a list of objects of the specified class
     */
    public static <T> List<T> jsonFileToObjectList(String file, Class<T> targetClass) {
        JSONArray jsonArray = convertFileToJson(file);
        List<T> objectList = new ArrayList<>();

        for (Object item : jsonArray) {
            if (item instanceof JSONObject) {
                try {
                    T obj = OBJECT_MAPPER.convertValue(item, targetClass);
                    objectList.add(obj);
                } catch (IllegalArgumentException e) {
                    System.err.printf("Error mapping JSON object to class %s: %s%n", targetClass.getName(), e.getMessage());
                }
            } else {
                System.err.printf("Invalid item in JSON array: expected JSONObject, found %s%n", item.getClass().getName());
            }
        }

        return objectList;
    }


}
