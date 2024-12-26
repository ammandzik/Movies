package quiz.model;

import java.util.Map;

public class ScoreBoard {

    private ScoreBoard(){

    }

    private static Map<String, Integer> scoreboard;

    public static void displayScoreBoard(){

        System.out.println(scoreboard);
    }
}
