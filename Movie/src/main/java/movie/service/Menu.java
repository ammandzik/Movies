package movie.service;

import java.util.InputMismatchException;

import static movie.service.MovieService.*;
import static movie.service.SearchBarService.displaySearchResult;
import static movie.service.UserService.askForUserInput;
import static quiz.service.QuizService.displayQuizMenu;


public class Menu {

    private Menu() {

    }

    private static int userChoice;
    private static final String FILE_PATH = "Movie/src/main/resources/movies.json";

    private static final String DEFAULT_MESSAGE = "Incorrect option has been chosen.";
    private static final String DEFAULT_ERROR_MESSAGE = "Incorrect argument provided. Only numbers above are allowed.";

    public static void displayMenu() {

        try {

            entryMenu();

            do {

                userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1:
                        displaySubMenu();
                        break;
                    case 2:
                        displayQuizMenu();
                        break;
                    case 3:
                        System.out.printf("*** Your random title is: %s *** %n", getRandomTitle(FILE_PATH));
                        break;
                    case 4:
                        System.out.println("Please provide your search phrase: ");
                        displaySearchResult(askForUserInput().next(), FILE_PATH);
                        break;
                    case 0:
                        System.out.println("See you next time!");
                        break;
                    default:
                        System.out.println(DEFAULT_MESSAGE);
                        break;
                }

            } while (userChoice != 0);

        } catch (InputMismatchException e) {

            System.err.println(DEFAULT_ERROR_MESSAGE);
            displayMenu();

        }
    }

    private static void displaySubMenu() {

        try {

            moviesSubMenu();

            do {

                userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1:
                        displayCategoriesMenu();
                        break;
                    case 2:
                        displayTitles(movieByRating(FILE_PATH));
                        break;
                    case 3:
                        displayTitles(movieByReleaseDate(FILE_PATH));
                        break;
                    case 0:
                        displayMenu();
                        break;
                    default:
                        System.out.println(DEFAULT_MESSAGE);
                        break;

                }

            } while (userChoice != 0);

        } catch (InputMismatchException e) {

            System.err.println(DEFAULT_ERROR_MESSAGE);
            displaySubMenu();

        }

    }

    private static void displayCategoriesMenu() {

        try {

            categoriesSubMenu();

            do {

                userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1:
                        displayTitles(movieByCategory("Adventure", FILE_PATH));
                        break;
                    case 2:
                        displayTitles(movieByCategory("Thriller", FILE_PATH));
                        break;
                    case 3:
                        displayTitles(movieByCategory("Sci-Fi", FILE_PATH));
                        break;
                    case 0:
                        displaySubMenu();
                        break;
                    default:
                        System.out.println(DEFAULT_MESSAGE);
                        break;

                }

            } while (userChoice != 0);

        } catch (InputMismatchException ex) {

            System.err.println(DEFAULT_ERROR_MESSAGE);

        }

    }

    private static void entryMenu() {

        System.out.println("""
                                
                Welcome to our movies library!
                Choose one of the below options:
                1.Movies titles
                2.Have some fun - take a movie knowledge Quiz!
                3.Choose random title for today
                4.Search for movie title
                5.Quit - press 0
                                
                """);

    }

    private static void moviesSubMenu() {
        System.out.println("""
                                
                1.Movies by categories
                2.Movies by ratings
                3.Movies by release date
                4.Quit to main menu - press 0
                                
                """);
    }

    private static void categoriesSubMenu() {
        System.out.println("""
                                
                1.Adventure
                2.Thriller
                3.Sci-Fi
                4.Previous page - press 0
                                
                """);
    }


}
