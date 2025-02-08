package movie.service;

import movie.model.Movie;

import java.util.List;
import java.util.Random;


import static java.util.Comparator.comparing;
import static movie.service.FileService.jsonFileToObjectList;

/**
 * Service class for managing movie-related operations, including filtering,
 * sorting, and retrieving movies.
 */
class MovieService {

    private static List<Movie> cachedMovies;
    private static final Random RANDOM = new Random();

    private MovieService() {}

    /**
     * Returns a formatted string of a random movie title from the provided file path.
     *
     * @param filePath the file path to the movie JSON file
     * @return a formatted string representing a random movie title
     */
    public static String getRandomTitle(String filePath) {
        return formatMovie(generateRandomMovie(filePath));
    }

    /**
     * Displays the list of movie titles to the console in a formatted manner.
     *
     * @param titles the list of movies to display
     */
    public static void displayTitles(List<Movie> titles) {
        formatTitles(titles).forEach(System.out::println);
    }

    /**
     * Filters movies by category, sorts them by release date, and returns the result.
     *
     * @param category the category to filter movies by
     * @param filePath the file path to the movie JSON file
     * @return a sorted list of movies matching the category
     */
    public static List<Movie> movieByCategory(String category, String filePath) {
        return getMovies(filePath)
                .stream()
                .filter(movie -> category.equals(movie.getCategory().getDescription()))
                .sorted(comparing(Movie::getReleaseDate))
                .toList();
    }

    /**
     * Sorts movies by release date (then by title as a secondary key) and returns the result.
     *
     * @param filePath the file path to the movie JSON file
     * @return a sorted list of movies by release date
     */
    public static List<Movie> movieByReleaseDate(String filePath) {
        return getMovies(filePath)
                .stream()
                .sorted(comparing(Movie::getReleaseDate).thenComparing(Movie::getTitle))
                .toList();
    }

    /**
     * Sorts movies by rating in descending order and returns the result.
     *
     * @param filePath the file path to the movie JSON file
     * @return a sorted list of movies by rating
     */
    public static List<Movie> movieByRating(String filePath) {
        return getMovies(filePath)
                .stream()
                .sorted(comparing(Movie::getRating).reversed())
                .toList();
    }


    // Private helper methods

    /**
     * Formats a single movie into a readable string.
     *
     * @param movie the movie to format
     * @return the formatted string
     */
    private static String formatMovie(Movie movie) {
        return String.format("%s (%s)", movie.getTitle(), movie.getReleaseDate());
    }

    /**
     * Formats a list of movies into a list of readable strings.
     *
     * @param list the list of movies to format
     * @return the list of formatted strings
     */
    private static List<String> formatTitles(List<Movie> list) {
        return list.stream()
                .map(movie -> String.format("Category: %s, Title: %s, Rating: *%.1f, Release date: %s",
                        movie.getCategory(), movie.getTitle(), movie.getRating(), movie.getReleaseDate()))
                .toList();
    }

    /**
     * Generates a random movie from the list of available movies.
     *
     * @param filePath the file path to the movie JSON file
     * @return a randomly selected Movie object
     */
    private static Movie generateRandomMovie(String filePath) {
        var allMovies = getMovies(filePath);

        if (allMovies.isEmpty()) {
            throw new IllegalStateException("No movies available to select from.");
        }

        return allMovies.get(RANDOM.nextInt(allMovies.size()));
    }

    /**
     * Retrieves all movies from the JSON file. Uses a cached version if already loaded.
     *
     * @param filePath the file path to the movie JSON file
     * @return the list of movies
     */
    private static List<Movie> getMovies(String filePath) {
        if (cachedMovies == null) {
            cachedMovies = safeJsonFileToObjectList(filePath);
        }
        return cachedMovies;
    }

    /**
     * Safely converts the JSON file to a list of Movie objects, logging any errors encountered.
     *
     * @param filePath the file path to the movie JSON file
     * @return the list of movies, or an empty list if an error occurs
     */
    private static List<Movie> safeJsonFileToObjectList(String filePath) {
        try {
            return jsonFileToObjectList(filePath, Movie.class);
        } catch (Exception e) {
            System.err.printf("Error loading movies from file %s: %s%n", filePath, e.getMessage());
            return List.of();
        }
    }


}