package quiz.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class for managing and displaying the scoreboard in the quiz game.
 * The scoreboard stores player names and their respective scores in descending order.
 */
public class ScoreBoard {

    private ScoreBoard() {
    }

    private static Map<String, Integer> scoresTable = new TreeMap<>(Comparator.reverseOrder());

    public static void addScore(Player player) {
        scoresTable.put(player.getName(), player.getScore());
    }

    public static void displayScoreBoard() {
        System.out.println("*** BEST SCORES TABLE ***");

        scoresTable.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(System.out::println);
    }


}