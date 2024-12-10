package movie.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static int userChoice;

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
                    System.out.println("***Categories");
                    break;
                case 2:
                    System.out.println("***Ratings");
                    break;
                case 3:
                    System.out.println("***Release date");
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

    private static void entryMenu() {

        System.out.println("Welcome to our movies library! \n\n" +
                "Choose one of the below options: \n" +
                "1.Movies titles \n" +
                "2.Have some fun - take a movie knowledge Quiz! \n" +
                "3.Quit - press 0");

    }

    private static void moviesSubMenu() {
        System.out.println("1.Movies by categories \n" +
                "2.Movies by ratings \n" +
                "3.Movies by release date \n" +
                "4.Quit to main menu - press 0");
    }


}
