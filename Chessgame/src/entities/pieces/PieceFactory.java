package entities.pieces;

import entities.pieces.concretepieces.Bishop;
import entities.pieces.concretepieces.King;
import entities.pieces.concretepieces.Knight;
import entities.pieces.concretepieces.Pawn;
import entities.pieces.concretepieces.Queen;
import entities.pieces.concretepieces.Rook;
import enums.Color;

public class PieceFactory {

    public static Piece createPiece(String pieceType, Color color) {
        return switch (pieceType.toLowerCase()) {
            case "king" -> new King(color);
            case "queen" -> new Queen(color);
            case "bishop" -> new Bishop(color);
            case "knight" -> new Knight(color);
            case "rook" -> new Rook(color);
            case "pawn" -> new Pawn(color);
            default -> throw new IllegalArgumentException("Unknown piece type: " + pieceType);
        };
    }
}
