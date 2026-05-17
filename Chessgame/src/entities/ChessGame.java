package entities;

import entities.player.Player;
import entities.player.strategies.AiPlayerStrategy;
import entities.player.strategies.HumanPlayerStrategy;
import enums.ChessGameState;
import enums.Color;

import java.util.ArrayList;

import static enums.ChessGameState.ACTIVE;

public class ChessGame implements BoardGames {

    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentPlayer;
    private ChessGameState gameState;
    private ArrayList<Move> gameLog;

    public ChessGame(String whitePlayerName, String blackPlayerName) {
        this.board = new Board();
        this.whitePlayer = new Player(whitePlayerName, Color.WHITE, new HumanPlayerStrategy());
        this.blackPlayer = new Player(blackPlayerName, Color.BLACK, new AiPlayerStrategy());
        this.currentPlayer = whitePlayer;
        this.gameState = ACTIVE;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessGameState getGameState() {
        return gameState;
    }

    public void setGameState(ChessGameState gameState) {
        this.gameState = gameState;
    }

    public void start() {
        while (this.gameState == ACTIVE) {
            Move move = currentPlayer.getMove();
            makeMove(move);
        }
    }

    private synchronized void makeMove(Move move) {
        if (move.isValid()) {
            gameLog.add(move);
            // make move
            // add move to gameLog
            // switch game state
            // switch current player
        }
    }

    private void switchTurn() {
        currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    private boolean isGameOver() {
        return isCheckmate(whitePlayer.getColor()) || isCheckmate(blackPlayer.getColor()) ||
               isStalemate(whitePlayer.getColor()) || isStalemate(blackPlayer.getColor());
    }

    public boolean isCheckmate(Color color) {
        return false;
    }

    public boolean isStalemate(Color color) {
        return false;
    }

}
