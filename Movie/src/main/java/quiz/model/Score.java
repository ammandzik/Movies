package quiz.model;

/**
 * Utility class for managing and displaying scores in the quiz.
 */
public class Score {

    private Score(){}

    public static void displayScore(Player player, Quiz quiz) {
        System.out.printf("Quiz Finished. Your score is %d/%d %n", player.getScore(), quiz.getPoints());
    }
}
