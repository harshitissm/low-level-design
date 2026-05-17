package entities.pieces.concretepieces;

import entities.Board;
import entities.Cell;
import entities.pieces.Piece;
import enums.Color;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public boolean canMove(Board board, Cell startBlock, Cell endBlock) {
        return false;
    }
}
