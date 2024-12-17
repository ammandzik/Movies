package movie.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import static movie.service.MovieService.*;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static int userChoice;
    private final static String FILE_PATH = "Movie/src/main/resources/movies.json";

    public static void displayMenu() {

        try {

            do {
                entryMenu();
                userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 1:
                        displaySubMenu();
                        break;
                    case 2:
                        System.out.println("Place for quiz");
                        break;
                    case 3:
                        getRandomTitle(FILE_PATH);
                    case 0:
                        System.out.println("See you next time!");
                        break;
                    default:
                        System.out.println("Incorrect option has been chosen.");
                        break;
                }

            } while (userChoice != 0);

        } catch (InputMismatchException e) {

            throw new IllegalArgumentException("Incorrect argument provided by user. Should accept only numbers.");

        }
    }

    private static void displaySubMenu() {

        do {

            moviesSubMenu();

            userChoice = scanner.nextInt();

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

    }

    private static void displayCategoriesMenu() {

        do {

            categoriesSubMenu();

            userChoice = scanner.nextInt();

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

    }

    private static void entryMenu() {

        System.out.println("Welcome to our movies library! \n\n" +
                "Choose one of the below options: \n" +
                "1.Movies titles \n" +
                "2.Have some fun - take a movie knowledge Quiz! \n" +
                "3.Choose random title for today \n" +
                "4.Search for movie title \n" +
                "5.Quit - press 0");

    }

    private static void moviesSubMenu() {
        System.out.println("1.Movies by categories \n" +
                "2.Movies by ratings \n" +
                "3.Movies by release date \n" +
                "4.Quit to main menu - press 0");
    }

    private static void categoriesSubMenu() {
        System.out.println("1.Adventure \n" +
                "2.Thriller \n" +
                "3.Sci-Fi \n" +
                "4.Previous page - press 0");
    }


}
