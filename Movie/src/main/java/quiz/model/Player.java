package quiz.model;

/**
 * Represents a player in the quiz game.
 * A player has a name and a score that tracks their performance.
 */
public class Player {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}