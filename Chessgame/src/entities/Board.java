package entities;

import entities.pieces.Piece;
import entities.pieces.PieceFactory;

import static enums.Color.BLACK;
import static enums.Color.WHITE;

public class Board {

    private final Cell[][] board;

    public Board() {
        board = new Cell[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Cell(row, col);
            }
        }

        setupPieces();
    }

    private void setupPieces() {
        // Add pawns and main pieces for both sides
        for (int j = 0; j < 8; j++) {
            board[1][j].setPiece(PieceFactory.createPiece("pawn", WHITE));
            board[6][j].setPiece(PieceFactory.createPiece("pawn", BLACK));
        }

        // Initialize white pieces
        board[0][0].setPiece(PieceFactory.createPiece("rook", WHITE));
        board[0][1].setPiece(PieceFactory.createPiece("knight", WHITE));
        board[0][2].setPiece(PieceFactory.createPiece("bishop", WHITE));
        board[0][3].setPiece(PieceFactory.createPiece("queen", WHITE));
        board[0][4].setPiece(PieceFactory.createPiece("king", WHITE));
        board[0][5].setPiece(PieceFactory.createPiece("bishop", WHITE));
        board[0][6].setPiece(PieceFactory.createPiece("knight", WHITE));
        board[0][7].setPiece(PieceFactory.createPiece("rook", WHITE));

        // Initialize black pieces
        board[7][0].setPiece(PieceFactory.createPiece("rook", BLACK));
        board[7][1].setPiece(PieceFactory.createPiece("knight", BLACK));
        board[7][2].setPiece(PieceFactory.createPiece("bishop", BLACK));
        board[7][3].setPiece(PieceFactory.createPiece("queen", BLACK));
        board[7][4].setPiece(PieceFactory.createPiece("king", BLACK));
        board[7][5].setPiece(PieceFactory.createPiece("bishop", BLACK));
        board[7][6].setPiece(PieceFactory.createPiece("knight", BLACK));
        board[7][7].setPiece(PieceFactory.createPiece("rook", BLACK));
    }

    public Cell getCell(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            return board[row][col];
        }
        return null;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col].getPiece();
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col].setPiece(piece);
    }

}
