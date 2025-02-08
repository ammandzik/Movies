package quiz.model;

/**
 * Represents a quiz in the game. The quiz tracks the player and their maximum points.
 */
public class Quiz {

    private int maxPoints;
    private Player player;

    public Quiz(Player player) {
        this.player = player;
    }

    public int getPoints() {
        return maxPoints;
    }

    public void setPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}