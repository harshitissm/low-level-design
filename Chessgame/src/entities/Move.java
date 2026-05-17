package entities;

public class Move {

    private final Cell startCell;
    private final Cell endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public boolean isValid() {
        if(endCell.getPiece() == null) return true;
        else return !(startCell.getPiece().isWhite() == endCell.getPiece().isWhite());
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }
}
