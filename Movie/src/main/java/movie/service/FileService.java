package movie.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileService {

    public static JSONArray convertFileToJson(String fileName) {

        JSONArray jsonArray = null;
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

    public static <T> List<T> jsonFileToObjectList(String file) throws IndexOutOfBoundsException {

        var jsonArray = (JSONArray) convertFileToJson(file);

        List<T> objects = new ArrayList<>();

        for (Object o : jsonArray) {

            objects.add((T) o);
        }

        return jsonArray;

    }


}
