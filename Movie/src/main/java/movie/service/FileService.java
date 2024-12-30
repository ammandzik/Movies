package movie.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import movie.advice.FileParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private FileService() {

    }

    public static JSONArray convertFileToJson(String fileName) {


        var jsonParser = new JSONParser();

        try (FileReader fr = new FileReader(fileName)) {

            return (JSONArray) jsonParser.parse(fr);

        } catch (FileNotFoundException e) {

            System.err.println("File was not found.");

        } catch (IOException e) {

            System.err.println(e.getMessage());

        } catch (ParseException e) {

            throw new FileParseException();

        }

        return new JSONArray();

    }

    public static <T> List<T> jsonFileToObjectList(String file, Class<T> customClass) {

        var jsonArray = convertFileToJson(file);

        List<T> objects = new ArrayList<>();

        var objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        try {

            for (Object o : jsonArray) {

                if (o instanceof JSONObject) {

                    T obj = objectMapper.convertValue(o, customClass);
                    objects.add(obj);
                }

            }

        } catch (ClassCastException ex) {

            System.err.println("Object of class JSONObject cannot be casted to a custom class.");

        }

        return objects;

    }


}
