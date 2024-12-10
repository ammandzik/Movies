package movie.service;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileServiceTest {

    private final String FILE_PATH = "movies.json";


    @Test
    public void readsFromFileCorrectly() {

        //given


        //when


        //then


    }

    @Test
    public void readingFileMethodThrowsFileNotFoundException() {

        //given

        final String NON_EXISTING_FILE = "bestMovies.json";

        //when

        var exception = assertThrows(FileNotFoundException.class, () -> FileService.readFromFile(NON_EXISTING_FILE));

        //then

        assertThat(exception.getMessage().equals("File does not exist."));


    }
}
