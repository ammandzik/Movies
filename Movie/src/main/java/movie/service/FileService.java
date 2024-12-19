package movie.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileService {

    public static JSONArray convertFileToJson(String fileName) {

        JSONArray jsonArray;

        var jsonParser = new JSONParser();

        try {

            jsonArray = (JSONArray) jsonParser.parse(new FileReader(fileName));

        } catch (FileNotFoundException e) {

            throw new RuntimeException("File does not exist.");

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());

        } catch (ParseException e) {

            throw new RuntimeException("Could not parse file.");
        }


        return jsonArray;
    }

    public static <T> List<T> jsonFileToObjectList(String file, Class<T> customClass) throws IndexOutOfBoundsException {

        var jsonArray = (JSONArray) convertFileToJson(file);

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

            throw new RuntimeException("Object of class JSONObject cannot be casted to custom class.");
        }

        return objects;

    }


}
