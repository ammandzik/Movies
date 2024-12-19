package movie.service;

import movie.model.Movie;

import java.util.List;

import static java.util.Comparator.comparing;
import static movie.service.FileService.jsonFileToObjectList;
import static movie.service.MovieService.displayTitles;

class SearchBarService {

    public static List<Movie> searchForTitle(String userInput, String filePath) {

        List<Movie> movies = jsonFileToObjectList(filePath, Movie.class)
                .stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(userInput.toLowerCase()))
                .sorted(comparing(Movie::getReleaseDate))
                .toList();

        if (movies.isEmpty()) {

            errorMessage();

        } else {

            successMessage();
            displayTitles(movies);

        }

        return movies;

    }

    private static void successMessage() {

        System.out.println("Please find your title/titles below:\n");

    }

    private static void errorMessage() {

        System.out.println("Sorry. No movies were found with given phrase.\n");

    }

}
