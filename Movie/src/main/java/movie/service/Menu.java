package movie.service;

import java.util.InputMismatchException;

import static movie.service.MovieService.*;
import static movie.service.SearchBarService.displaySearchResult;
import static movie.service.UserService.askForUserInput;
import static quiz.service.QuizService.displayQuizMenu;

/**
 * The Menu class provides a user interface for interacting with the movie library application.
 * It includes options to view movie titles, take a quiz, get a random movie title, and search for movies.
 * This class is designed as a utility class and cannot be instantiated.
 */
public class Menu {

    private static final String FILE_PATH = "Movie/src/main/resources/movies.json";
    private static final String DEFAULT_MESSAGE = "Incorrect option has been chosen.";
    private static final String DEFAULT_ERROR_MESSAGE = "Incorrect argument provided. Only numbers are allowed.";

    private Menu() {}


    /**
     * Displays the main menu of the application and handles user input.
     * Allows the user to navigate to submenus or perform specific actions like searching or getting a random title.
     */
    public static void displayMenu() {
        while (true) {
            try {
                entryMenu();
                int userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1 -> displaySubMenu();
                    case 2 -> displayQuizMenu();
                    case 3 -> System.out.printf("*** Your random title is: %s *** %n", getRandomTitle(FILE_PATH));
                    case 4 -> {
                        System.out.println("Please provide your search phrase: ");
                        displaySearchResult(askForUserInput().next(), FILE_PATH);
                    }
                    case 0 -> {
                        System.out.println("See you next time!");
                        return;
                    }
                    default -> System.out.println(DEFAULT_MESSAGE);
                }
            } catch (InputMismatchException e) {
                System.err.println(DEFAULT_ERROR_MESSAGE);
                askForUserInput().nextLine(); // Clear the invalid input
                entryMenu();
            }
        }
    }

    /**
     * Displays the movies submenu and handles user input.
     * Allows the user to navigate to categories, view movies by ratings, or view movies by release date.
     */
    private static void displaySubMenu() {
        while (true) {
            try {
                moviesSubMenu();
                int userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1 -> displayCategoriesMenu();
                    case 2 -> displayTitles(movieByRating(FILE_PATH));
                    case 3 -> displayTitles(movieByReleaseDate(FILE_PATH));
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println(DEFAULT_MESSAGE);
                }
            } catch (InputMismatchException e) {
                System.err.println(DEFAULT_ERROR_MESSAGE);
                askForUserInput().nextLine(); // Clear the invalid input
            }
        }
    }

    /**
     * Displays the categories submenu and handles user input.
     * Allows the user to view movies by specific categories such as Adventure, Thriller, and Sci-Fi.
     */
    private static void displayCategoriesMenu() {
        while (true) {
            try {
                categoriesSubMenu();
                int userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1 -> displayTitles(movieByCategory("Adventure", FILE_PATH));
                    case 2 -> displayTitles(movieByCategory("Thriller", FILE_PATH));
                    case 3 -> displayTitles(movieByCategory("Sci-Fi", FILE_PATH));
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println(DEFAULT_MESSAGE);
                }
            } catch (InputMismatchException ex) {
                System.err.println(DEFAULT_ERROR_MESSAGE);
                askForUserInput().nextLine(); // Clear the invalid input
            }
        }
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void entryMenu() {
        System.out.println("""

                Welcome to our movies library!
                Choose one of the below options:
                1. Movies titles
                2. Have some fun - take a movie knowledge Quiz!
                3. Choose random title for today
                4. Search for movie title
                5. Quit - press 0

                """);
    }

    /**
     * Prints the movies submenu options to the console.
     */
    private static void moviesSubMenu() {
        System.out.println("""

                1. Movies by categories
                2. Movies by ratings
                3. Movies by release date
                4. Quit to main menu - press 0

                """);
    }

    /**
     * Prints the categories submenu options to the console.
     */
    private static void categoriesSubMenu() {
        System.out.println("""

                1. Adventure
                2. Thriller
                3. Sci-Fi
                4. Previous page - press 0

                """);
    }


}