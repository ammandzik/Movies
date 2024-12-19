package movie.service;

import java.util.InputMismatchException;

import static movie.service.MovieService.*;
import static movie.service.SearchBarService.searchForTitle;
import static movie.service.UserService.askForUserInput;

public class Menu {
    private static int userChoice;
    private final static String FILE_PATH = "Movie/src/main/resources/movies.json";

    public static void displayMenu() {

        try {

            do {
                entryMenu();
                userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1:
                        displaySubMenu();
                        break;
                    case 2:
                        System.out.println("Place for quiz");
                        break;
                    case 3:
                        System.out.println("*** Your random title is: " + getRandomTitle(FILE_PATH) + " *** \n");
                        break;
                    case 4:
                        System.out.println("Please provide your search phrase: ");
                        searchForTitle(askForUserInput().next(), FILE_PATH);
                        break;
                    case 0:
                        System.out.println("See you next time!");
                        break;
                    default:
                        System.out.println("Incorrect option has been chosen.");
                        break;
                }

            } while (userChoice != 0);

        } catch (InputMismatchException e) {

            System.out.println("Incorrect argument provided. Only numbers above are allowed.");
            displayMenu();

        }
    }

    private static void displaySubMenu() {

        try {

            do {

                moviesSubMenu();

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
                        System.out.println("Incorrect option has been chosen.");
                        break;

                }

            } while (userChoice != 0);

        } catch (InputMismatchException e) {

            System.out.println("Incorrect argument provided. Only numbers above are allowed.");

        } finally {

            displaySubMenu();
        }

    }

    private static void displayCategoriesMenu() {

        try {
            do {

                categoriesSubMenu();

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
                        System.out.println("Incorrect option has been chosen.");
                        break;

                }

            } while (userChoice != 0);

        } catch (InputMismatchException ex) {

            System.out.println("Incorrect argument provided. Only numbers above are allowed.");

        } finally {

            displayCategoriesMenu();
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
