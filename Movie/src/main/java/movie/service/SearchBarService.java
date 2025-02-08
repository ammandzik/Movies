package movie.service;

import movie.model.Movie;

import java.util.List;

import static java.util.Comparator.comparing;
import static movie.service.FileService.jsonFileToObjectList;
import static movie.service.MovieService.displayTitles;

/**
 * Service responsible for handling search functionality for movies.
 */
class SearchBarService {

    private SearchBarService() {}

    /**
     * Searches for movies by title based on user input and displays the results.
     *
     * @param userInput The search phrase provided by the user.
     * @param filePath  The file path to the JSON file containing movie data.
     * @return A list of movies matching the search phrase.
     */
    public static List<Movie> displaySearchResult(String userInput, String filePath) {
        List<Movie> movies = searchForTitle(userInput, filePath);

        if (movies.isEmpty()) {
            printErrorMessage();
        } else {
            printSuccessMessage();
            displayTitles(movies);
        }

        return movies;
    }

    /**
     * Searches for movies containing the given title fragment (case insensitive).
     *
     * @param userInput The search phrase provided by the user.
     * @param filePath  The file path to the JSON file containing movie data.
     * @return A list of movies matching the search phrase, sorted by release date.
     */
    private static List<Movie> searchForTitle(String userInput, String filePath) {
        return jsonFileToObjectList(filePath, Movie.class)
                .stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(userInput.toLowerCase()))
                .sorted(comparing(Movie::getReleaseDate))
                .toList();
    }

    /**
     * Prints a success message when movies are found.
     */
    private static void printSuccessMessage() {
        System.out.println("Please find your title/titles below:\n");
    }

    /**
     * Prints an error message when no movies are found.
     */
    private static void printErrorMessage() {
        System.err.println("Sorry. No movies were found with the given phrase.\n");
    }

}