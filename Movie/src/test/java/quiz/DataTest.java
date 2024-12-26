package quiz;

import quiz.model.Player;

import java.util.Set;
import java.util.TreeSet;

public class DataTest {

    public static Set<String> testAnswers = new TreeSet<>();

    public static void fillSet(){

        testAnswers.add("a");
        testAnswers.add("c");


    }

    public static Player createPlayer() {

        var player = new Player();

        return player;
    }
}
