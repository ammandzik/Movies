package movie.service;

import movie.model.Movie;

import java.util.List;
import java.util.Random;

import static java.util.Comparator.comparing;
import static movie.service.FileService.jsonFileToObjectList;

class MovieService {

    private static List<Movie> cachedMovies;
    private static final Random RANDOM = new Random();

    public static String getRandomTitle(String filePath) {

        return formatMovie(generateRandomMovie(filePath));

    }

    public static void displayTitles(List<Movie> titles) {

        formatTitles(titles).forEach(System.out::println);
    }

    private static String formatMovie(Movie movie) {

        return movie.getTitle() + " (" + movie.getReleaseDate() + ")";
    }

    private static List<String> formatTitles(List<Movie> list) {

        return list.stream()
                .map(movie -> "Category: " + movie.getCategory() +
                        ", Title: " + movie.getTitle() +
                        ", Rating: *" + movie.getRating() +
                        ", Release date: " + movie.getReleaseDate())
                .toList();

    }

    public static List<Movie> movieByCategory(String category, String filePath) {

        return getMovies(filePath)
                .stream()
                .filter(movie -> category.equals(movie.getCategory().getDescription()))
                .sorted(comparing(Movie::getReleaseDate))
                .toList();


    }

    public static List<Movie> movieByReleaseDate(String filePath) {

        return getMovies(filePath)
                .stream()
                .sorted(comparing(Movie::getReleaseDate).thenComparing(Movie::getTitle))
                .toList();

    }

    public static List<Movie> movieByRating(String filePath) {


        return getMovies(filePath)
                .stream()
                .sorted(comparing(Movie::getRating).reversed())
                .toList();


    }

    private static Movie generateRandomMovie(String filePath) {

        var allMovies = getMovies(filePath);

        if (allMovies.isEmpty()) {

            throw new IllegalStateException("No movies available to select from.");
        }

        return allMovies.get(RANDOM.nextInt(allMovies.size()));


    }

    private static List<Movie> getMovies(String filePath) {

        if (cachedMovies == null) {

            cachedMovies = safeJsonFileToObjectList(filePath);
        }

        return cachedMovies;
    }

    private static List<Movie> safeJsonFileToObjectList(String filePath) {

        try {

            return jsonFileToObjectList(filePath, Movie.class);

        } catch (Exception e) {

            System.err.println("Error loading movies: " + e.getMessage());

            return List.of();
        }
    }


}
