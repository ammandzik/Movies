package quiz.service;

import quiz.model.Answer;
import quiz.model.Player;
import quiz.model.Question;
import quiz.model.Quiz;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static movie.service.Menu.displayMenu;
import static movie.service.UserService.askForUserInput;
import static quiz.model.Score.displayScore;
import static quiz.model.ScoreBoard.addScore;
import static quiz.model.ScoreBoard.displayScoreBoard;
import static quiz.service.PlayerService.*;
import static quiz.service.QuestionService.addQuestionsToPool;

/**
 * Service class for managing the quiz game.
 * Handles quiz operations such as displaying the quiz, tracking answers, and calculating scores.
 */
public class QuizService {

    private static final String FILE_PATH = "Movie/src/main/resources/questions.json";
    private static boolean quizOver = false;

    private QuizService() {}

    /**
     * Main method to play the quiz. Sets up the player and quiz, and manages the game loop.
     */
    public static void playQuiz() {
        var player = new Player();
        var quiz = new Quiz(player);

        do {
            player.setName(saveUserName());
            displayQuiz(addQuestionsToPool(FILE_PATH), player, quiz);
            displayScore(player, quiz);

        } while (!quizOver);

        addScore(player);
    }

    /**
     * Displays the quiz by iterating through questions and tracking answers.
     *
     * @param questions the list of quiz questions.
     * @param player    the player object.
     * @param quiz      the quiz object.
     */
    private static void displayQuiz(List<Question> questions, Player player, Quiz quiz) {
        trackPlayersGuessedAnswers(questions, player, quiz);
        quizOver = true;
    }

    /**
     * Tracks and processes the player's guessed answers, updating their score.
     *
     * @param questions the list of quiz questions.
     * @param player    the player object.
     * @param quiz      the quiz object.
     */
    private static void trackPlayersGuessedAnswers(List<Question> questions, Player player, Quiz quiz) {
        for (int i = 0; i < questions.size(); i++) {
            TreeSet<String> correctAnswers = addQuestionsCorrectAnswersToSet(i, questions);
            TreeSet<String> withoutGuessedAnswers = addQuestionsCorrectAnswersToSet(i, questions);

            System.out.println("*********************************************** \n" +
                    questions.get(i).getQuestionText());

            getAndCountPointsForCorrectAnswers(quiz, player, correctAnswers, withoutGuessedAnswers);
        }
    }

    /**
     * Handles the player's guesses and calculates points for correct answers.
     *
     * @param quiz              the quiz object.
     * @param player            the player object.
     * @param correctAnswers    the set of correct answers.
     * @param remainingAnswers  the set of remaining answers to track guesses.
     */
    private static void getAndCountPointsForCorrectAnswers(Quiz quiz, Player player, Set<String> correctAnswers, Set<String> remainingAnswers) {
        for (int x = 0; x < correctAnswers.size(); x++) {
            incrementMaxQuizPoints(quiz);
            var playerGuess = getUserAnswer();
            countScore(remainingAnswers, playerGuess, player);
        }
        System.out.printf("Correct answer/-s %s %n", correctAnswers);
    }

    /**
     * Increments the maximum number of points for the quiz.
     *
     * @param quiz the quiz object.
     */
    private static void incrementMaxQuizPoints(Quiz quiz) {
        quiz.setPoints(quiz.getPoints() + 1);
    }


    /**
     * Builds a set of correct answers for a specific question.
     *
     * @param questionIndex the index of the question in the list.
     * @param questions     the list of quiz questions.
     * @return a set of correct answers for the question.
     */
    private static TreeSet<String> addQuestionsCorrectAnswersToSet(int questionIndex, List<Question> questions) {
        TreeSet<String> correctAnswers = new TreeSet<>();
        List<Answer> answers = questions.get(questionIndex).getAnswers();

        for (Answer answer : answers) {
            correctAnswers.add(answer.correctAnswer());
        }

        return correctAnswers;

    }


    /**
     * Displays the quiz menu and handles user input.
     */
    public static void displayQuizMenu() {
        try {
            quizMenu();

            int userChoice;
            do {
                userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1 -> playQuiz();
                    case 2 -> displayScoreBoard();
                    case 0 -> displayMenu();
                    default -> System.out.println("Incorrect option has been chosen.");
                }
            } while (userChoice != 0);
        } catch (InputMismatchException e) {
            System.err.println("Incorrect argument provided. Only numbers are allowed.");
            askForUserInput().nextLine(); // Clear invalid input
            quizMenu();
        }
    }

    /**
     * Displays the quiz menu options to the console.
     */
    private static void quizMenu() {
        System.out.println("""
                1. Start Quiz
                2. Check Best Scores
                3. Press 0 for Exit
                """);
    }
}