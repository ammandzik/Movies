package movie.service;

import movie.model.Movie;

import java.util.List;

import static java.util.Comparator.comparing;
import static movie.service.FileService.jsonFileToObjectList;
import static movie.service.MovieService.displayTitles;

class SearchBarService {

    private SearchBarService() {

    }

    public static List<Movie> displaySearchResult(String userInput, String filePath) {

        var movies = searchForTitle(userInput, filePath);

        if (movies.isEmpty()) {

            errorMessage();

        } else {

            successMessage();
            displayTitles(movies);

        }

        return movies;

    }

    private static List<Movie> searchForTitle(String userInput, String filePath) {

        return jsonFileToObjectList(filePath, Movie.class)
                .stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(userInput.toLowerCase()))
                .sorted(comparing(Movie::getReleaseDate))
                .toList();

    }

    private static void successMessage() {

        System.out.println("Please find your title/titles below:\n");

    }

    private static void errorMessage() {

        System.err.println("Sorry. No movies were found with given phrase.\n");

    }

}
