package quiz.model;

public class Quiz {
    public Quiz(Player player) {

        this.player = player;
    }

    private int maxPoints;
    private Player player;

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
