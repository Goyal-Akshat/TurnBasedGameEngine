package org.akshat.api;

import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.Board;
import org.akshat.game.Cell;
import org.akshat.game.Move;
import org.akshat.game.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(board1.getCell(i, j) == null) {
                        return new Move(new Cell(i, j),computer);
                    }
                }
            }
            throw new IllegalArgumentException("Board is full");
        }
        else {
            throw new IllegalArgumentException("Invalid board type");
        }
    }
}
