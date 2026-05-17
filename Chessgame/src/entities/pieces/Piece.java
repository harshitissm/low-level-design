package entities.pieces;

import entities.Board;
import entities.Cell;
import enums.Color;

import static enums.Color.WHITE;

public abstract class Piece {

    private boolean killed;
    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public abstract Color getColor();

    public boolean isWhite() {
        return color == WHITE;
    }

    public abstract boolean canMove(Board board, Cell startBlock, Cell endBlock);

}
