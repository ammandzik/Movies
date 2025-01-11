package quiz.model;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class for managing and displaying the scoreboard in the quiz game.
 * The scoreboard stores player names and their respective scores in descending order.
 */
public class ScoreBoard {

    private ScoreBoard() {}

    private static Map<String, Integer> scoresTable = new TreeMap<>(Comparator.reverseOrder());

    public static void addScore(Player player) {
        scoresTable.put(player.getName(), player.getScore());
    }

    public static void displayScoreBoard() {
        System.out.println("*** BEST SCORES TABLE ***");

        for (Map.Entry<String, Integer> entry : scoresTable.entrySet()) {
            String player = entry.getKey();
            Integer score = entry.getValue();
            System.out.println("Player name: " + player + "*** Score: " + score);
        }
    }


}
