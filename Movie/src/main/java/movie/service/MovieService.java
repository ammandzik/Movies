package movie.service;

import movie.model.Movie;

import java.util.List;
import java.util.Random;

import static java.util.Comparator.comparing;
import static movie.service.FileService.jsonFileToObjectList;

class MovieService {

    public static String getRandomTitle(String filePath) {

        var movie = generateRandomMovie(filePath);

        return movie.getTitle() + " " + movie.getReleaseDate();

    }

    public static void displayTitles(List<Movie> list) {

        List<String> movies = list.stream()
                .map(movie -> "Category: " + movie.getCategory() + ", Title: " + movie.getTitle() + ", Rating: *" + movie.getRating() +
                        ", Release date: " + movie.getReleaseDate())
                .toList();

        movies.forEach(System.out::println);

    }

    public static List<Movie> movieByCategory(String category, String filePath) {

        var allMovies = jsonFileToObjectList(filePath, Movie.class);

        return allMovies
                .stream()
                .filter(movie -> category.equals(movie.getCategory().getDescription()))
                .sorted(comparing(Movie::getReleaseDate))
                .toList();


    }

    public static List<Movie> movieByReleaseDate(String filePath) {

        var allMovies = jsonFileToObjectList(filePath, Movie.class);

        return allMovies
                .stream()
                .sorted(comparing(Movie::getReleaseDate).thenComparing(Movie::getTitle))
                .toList();

    }

    public static List<Movie> movieByRating(String filePath) {

        var allMovies = jsonFileToObjectList(filePath, Movie.class);

        return allMovies
                .stream()
                .sorted(comparing(Movie::getRating).reversed())
                .toList();


    }

    private static Movie generateRandomMovie(String filePath) {

        var allMovies = jsonFileToObjectList(filePath, Movie.class);

        var random = new Random();

        var randomNumber = random.nextInt(allMovies.size());

        return allMovies.get(randomNumber);


    }


}
