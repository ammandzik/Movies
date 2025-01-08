package quiz.service;

import quiz.model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

class PlayerService {

    private PlayerService() {

    }

    private static final String ANSWER_REGEX = "[a-c]";
    private static final String NAME_REGEX = "^[a-zA-Z]+$";


    public static void countScore(Set<String> correctAnswers, String playerGuess, Player player) {

        if (answerCorrect(correctAnswers, playerGuess)) {

            player.setScore(player.getScore() + 1);
        }

    }


    public static boolean answerCorrect(Set<String> correctAnswers, String playerGuess) {

        return correctAnswers.remove(playerGuess);

    }


    public static String saveUserName() {


        var userAnswer = "";

        try {

            while (userAnswer.isEmpty() || !userAnswer.matches(NAME_REGEX)) {

                userAnswer = askForUserName().nextLine();

            }

        } catch (InputMismatchException ime) {

            System.err.println("Incorrect argument provided. Only letters are accepted.");
        }

        return userAnswer;

    }

    public static String getUserAnswer() {

        var userAnswer = "";

        try {

            while (userAnswer.isEmpty() || !userAnswer.matches(ANSWER_REGEX)) {

                userAnswer = askForUserInput().nextLine();

            }
        } catch (InputMismatchException ime) {

            System.err.println("Incorrect option has been chosen. Select one between a-c letters.");
        }

        return userAnswer;

    }

    private static Scanner askForUserInput() {

        System.out.println("Please select your answer/-s between a-c: ");

        return new Scanner(System.in);
    }

    private static Scanner askForUserName() {

        System.out.println("Please provide your name: ");

        return new Scanner(System.in);
    }


}
