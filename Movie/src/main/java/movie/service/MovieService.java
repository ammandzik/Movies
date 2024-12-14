package movie.service;

import movie.model.Movie;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MovieService {

    public static Movie getRandomTitle(String filePath){

        return generateRandomMovie(filePath);

    }

    public static <T> void displayTitles(List<T> list){

        list.forEach(System.out::println);


    }

    public static List<Movie> movieByCategory(String category, String filePath) {

        var allMovies = FileService.jsonFileToObjectList(filePath, Movie.class);

        var moviesByCategory = allMovies
                .stream()
                .filter(movie -> category.equals(movie.getCategory().getDescription()))
                .sorted(Comparator.comparing(Movie::getTitle))
                .toList();

        return moviesByCategory;

    }

    public static List<Movie> movieByReleaseDate(String filePath) {

        var allMovies = FileService.jsonFileToObjectList(filePath, Movie.class);

        allMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {

                return o1.getReleaseDate().compareTo(o2.getReleaseDate());
            }
        });


        return allMovies;
    }

    public static List<Movie> movieByRating(String filePath) {

        var allMovies = FileService.jsonFileToObjectList(filePath, Movie.class);

        allMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {

                return o2.getRating().compareTo(o1.getRating());
            }
        });

        return allMovies;


    }

    private static Movie generateRandomMovie(String filePath){

         var allMovies = FileService.jsonFileToObjectList(filePath, Movie.class);

         var random = new Random();

         var randomNumber = random.nextInt(allMovies.size());

         return allMovies.get(randomNumber);


    }




}
