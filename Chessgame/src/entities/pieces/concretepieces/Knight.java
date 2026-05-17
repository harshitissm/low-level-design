package entities.pieces.concretepieces;

import entities.Board;
import entities.Cell;
import entities.pieces.Piece;
import enums.Color;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean canMove(Board board, Cell startBlock, Cell endBlock) {
        return false;
    }
}
