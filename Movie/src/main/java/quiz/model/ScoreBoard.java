package quiz.model;

import java.util.Map;
import java.util.TreeMap;

public class ScoreBoard {


    private ScoreBoard() {

    }

    protected static Map<String, Integer> scoresTable = new TreeMap<>(

            (player1, player2) -> {

                int compareScore = player1.compareTo(player2);

                if (compareScore == 0) {

                    return player1.compareTo(player2);
                }

                return compareScore;

            }
    );

    public static void addScore(Player player) {

        scoresTable.put(player.getName(), player.getScore());
    }

    public static void displayScoreBoard() {

        System.out.println("*** BEST SCORES TABLE ***");

        for (Map.Entry<String, Integer> entry : scoresTable.entrySet()) {

            String player = entry.getKey();
            Integer score = entry.getValue();

            System.out.println("Player name: " + player + " Score: " + score);
        }
    }


}
