package quiz.service;

import quiz.model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Service class for managing player interactions in the quiz.
 * Handles operations such as validating answers, updating scores, and getting user input.
 */
class PlayerService {

    private PlayerService() {}

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ANSWER_REGEX = "[a-c]";
    private static final String NAME_REGEX = "^[a-zA-Z]+$";


    /**
     * Updates the player's score if their guess is correct.
     *
     * @param correctAnswers the set of correct answers.
     * @param playerGuess    the player's guessed answer.
     * @param player         the player whose score is being updated.
     */
    public static void countScore(Set<String> correctAnswers, String playerGuess, Player player) {
        if (isAnswerCorrect(correctAnswers, playerGuess)) {
            player.setScore(player.getScore() + 1);
        }
    }


    /**
     * Checks if the player's guessed answer is correct.
     *
     * @param correctAnswers the set of correct answers.
     * @param playerGuess    the player's guessed answer.
     * @return true if the guess is correct, false otherwise.
     */
    public static boolean isAnswerCorrect(Set<String> correctAnswers, String playerGuess) {
        return correctAnswers.remove(playerGuess);
    }

    /**
     * Prompts the user to enter their name and validates the input.
     *
     * @return the validated username.
     */
    public static String saveUserName() {
        String userName = "";

        try {
            while (userName.isEmpty() || !userName.matches(NAME_REGEX)) {
                System.out.println("Please provide your name: ");

                if (SCANNER.hasNextLine()) {
                    userName = SCANNER.nextLine().trim();

                    if (!userName.matches(NAME_REGEX)) {
                        System.err.println("Invalid name. Only letters are accepted.");
                    }
                } else {
                    System.err.println("No input found. Please try again.");
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Error reading input. Please try again.");
            SCANNER.nextLine(); // Clear invalid input
        }

        return userName;
    }

    /**
     * Prompts the user to select an answer and validates the input.
     *
     * @return the validated answer.
     */
    public static String getUserAnswer() {
        String userAnswer = "";

        try {
            while (userAnswer.isEmpty() || !userAnswer.matches(ANSWER_REGEX)) {
                System.out.println("Please select your answer (a, b, or c): ");

                if (SCANNER.hasNextLine()) {
                    userAnswer = SCANNER.nextLine().trim();

                    if (!userAnswer.matches(ANSWER_REGEX)) {
                        System.err.println("Invalid choice. Select a, b, or c.");
                    }
                } else {
                    System.err.println("No input found. Please try again.");
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Error reading input. Please try again.");
            SCANNER.nextLine(); // Clear invalid input
        }

        return userAnswer;
    }
}