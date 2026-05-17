package entities.player;

import entities.Move;
import entities.player.strategies.PlayerStrategy;
import enums.Color;

public class Player {

    private final String name;
    private final Color color;
    private final PlayerStrategy playerStrategy;

    public Player(String name, Color color, PlayerStrategy playerStrategy) {
        this.name = name;
        this.color = color;
        this.playerStrategy = playerStrategy;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Move getMove() {
        return playerStrategy.determineMove();
    }
}
