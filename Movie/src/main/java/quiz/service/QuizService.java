package quiz.service;


import quiz.model.Answer;
import quiz.model.Player;
import quiz.model.Question;
import quiz.model.Quiz;

import java.util.*;

import static movie.service.Menu.displayMenu;
import static movie.service.UserService.askForUserInput;
import static quiz.model.Score.displayScore;
import static quiz.model.ScoreBoard.addScore;
import static quiz.model.ScoreBoard.displayScoreBoard;
import static quiz.service.PlayerService.*;
import static quiz.service.QuestionService.addQuestionsToPool;

public class QuizService {

    private static final String FILE_PATH = "Movie/src/main/resources/questions.json";
    private static boolean quizOver = false;

    private QuizService() {

    }

    public static void playQuiz() {

        var quiz = new Quiz();
        var player = new Player();

        do {
            player.setName(saveUserName());
            displayQuiz(addQuestionsToPool(FILE_PATH), player, quiz);
            displayScore(player, quiz);

        } while (!quizOver);

        addScore(player);

    }

    public static void displayQuiz(List<Question> questions, Player player, Quiz quiz) {


        for (int i = 0; i < questions.size(); i++) {

            Set<String> correctAnswers = new TreeSet<>();
            Set<String> withoutGuessedAnswers = new HashSet<>();


            addQuestionCorrectAnswersToSet(i, questions, correctAnswers);
            addQuestionCorrectAnswersToSet(i, questions, withoutGuessedAnswers);

            System.out.println("***********************************************" +

                    questions.get(i).getQuestionText()

            );


            for (int x = 0; x < correctAnswers.size(); x++) {

                countMaxOfQuizPoints(quiz);
                var playerGuess = getUserAnswer();
                countScore(withoutGuessedAnswers, playerGuess, player);

            }

            System.out.printf("Correct answer/-s %s %n", correctAnswers);
        }

        quizOver = true;


    }

    private static void countMaxOfQuizPoints(Quiz quiz) {

        quiz.setPoints(quiz.getPoints() + 1);

    }

    private static void addQuestionCorrectAnswersToSet(int n, List<Question> questions, Set<String> correctAnswers) {


        List<Answer> answers = questions.get(n).getAnswers();

        for (int x = 0; x < answers.size(); x++) {

            correctAnswers.add(answers.get(x).correctAnswer());

            if (answers.size() > 1) {

                correctAnswers.add(answers.get(x).correctAnswer());
            }
        }

    }

    public static void displayQuizMenu() {

        int userChoice;

        try {

            quizMenu();

            do {

                userChoice = askForUserInput().nextInt();

                switch (userChoice) {
                    case 1:
                        playQuiz();
                        break;
                    case 2:
                        displayScoreBoard();
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

            System.err.println("Incorrect argument provided. Only numbers above are allowed.");
            quizMenu();

        }

    }

    private static void quizMenu() {

        System.out.println("""
                1. Start Quiz
                2. Check best scores
                3. Press 0 for Exit
                """);
    }
}
